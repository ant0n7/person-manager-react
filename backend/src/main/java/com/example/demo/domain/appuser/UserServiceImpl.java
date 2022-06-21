package com.example.demo.domain.appuser;

import com.example.demo.domain.appuser.dto.CreateUserDTO;
import com.example.demo.domain.appuser.dto.SubjectUserDTO;
import com.example.demo.domain.exceptions.InvalidEmailException;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.role.RoleRepository;
import com.example.demo.domain.subjects.Subject;
import com.example.demo.domain.subjects.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;

import java.util.*;

@Service @RequiredArgsConstructor @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubjectRepository subjectRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final String[] errorMessages = new String[]
            {"User not found", "Email is not valid", "Username already exists", "Email already exists"};

    @Override
//    This method is used for security authentication, use caution when changing this
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        else {
//          Construct a valid set of Authorities (needs to implement Granted Authorities)
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRolename()));
                role.getAuthorities().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
            });
//            return a spring internal user object that contains authorities and roles
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
            role.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .forEach(authorities::add);
        }
        return authorities;
    }

    @Override
    public User createUser(CreateUserDTO userDto) throws InstanceAlreadyExistsException, InvalidEmailException {
        if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
            throw new InvalidEmailException(errorMessages[1]);
        }
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new InstanceAlreadyExistsException(errorMessages[2]);
        }
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new InstanceAlreadyExistsException(errorMessages[3]);
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        //Set default role of every new user to USER
        User user = userMapper.createUserDTOToUser(userDto);
        user.setRoles(List.of(roleRepository.findByRolename("STUDENT")));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByRolename(rolename);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public Optional<User> findById(UUID id) throws InstanceNotFoundException{
        if (userRepository.existsById(id)){
            return userRepository.findById(id);
        }
        else {
            throw new InstanceNotFoundException("User not found");
        }
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UUID id, User user) throws InstanceNotFoundException, InvalidEmailException, InstanceAlreadyExistsException {
        if (!userRepository.existsById(id)) throw new InstanceNotFoundException("User does not exist.");

        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new InvalidEmailException(errorMessages[1]);
        }
        //When updating a user he needs the possibility to keep his username, but in case he changes it we need to
        // check if it's already in use
        if (userRepository.findByUsername(user.getUsername()) != null &&  /* true = username may be updated*/
                /*true = username does not belong to updated profile */
                !user.getUsername().equals(userRepository.findById(user.getId()).get().getUsername())) {
            throw new InstanceAlreadyExistsException(errorMessages[2]);
        }
        if (userRepository.findByEmail(user.getEmail()) != null &&  /* true = email maybe updated*/
                /*true = email does not belong to updated profile */
                !user.getEmail().equals(userRepository.findById(user.getId()).get().getEmail())) {
            throw new InstanceAlreadyExistsException(errorMessages[3]);
        }
        // If password is updated -> encrypt, else -> do nothing
        if (!(passwordEncoder.matches(/* Maybe updated password */ user.getPassword(),
                /* Old password */ userRepository.findById(user.getId()).get().getPassword()))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(UUID id) throws InstanceNotFoundException {
        if (!userRepository.existsById(id)) throw new InstanceNotFoundException("User does not exist.");
        userRepository.deleteById(id);
    }

    @Override
    public void addSubjectToUser(UUID userID, UUID subjectID) throws InstanceNotFoundException {
        User user;
        Subject subject;
        try {
            user = userRepository.findById(userID).get();
            subject = subjectRepository.findById(subjectID).get();
        } catch (Exception e){
            throw new InstanceNotFoundException();
        }
        user.getSubjects().add(subject);
    }
    @Override
    public void deleteSubjectFromUser(UUID userID, UUID subjectID) throws InstanceNotFoundException {
        User user;
        Subject subject;
        try {
            user = userRepository.findById(userID).get();
            subject = subjectRepository.findById(subjectID).get();
        } catch (Exception e){
            throw new InstanceNotFoundException();
        }

        user.getSubjects().remove(subject);
    }

    @Override
    public SubjectUserDTO findSubjectsById(UUID id) throws InstanceNotFoundException {
        try {
            User user = userRepository.findById(id).get();
            return userMapper.userToSubjectUserDTO(user);
        } catch (Exception e){
            throw new InstanceNotFoundException("No user found with the given ID");
        }
    }
    @Override
    public SubjectUserDTO findSubjectsByUsername(String username) throws InstanceNotFoundException{
        try {
            User user = userRepository.findByUsername(username);
            user.getId();
            return userMapper.userToSubjectUserDTO(user);
        } catch (Exception e){
            throw new InstanceNotFoundException("No user found with the given username");
        }
    }

    @Override
    public List<User> findUsersBySubject(UUID id) throws InstanceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (subjectRepository.existsById(id)){
            List<UUID> users = userRepository.getStudentsBySubject(id);
            List<UUID> students = new ArrayList<>();
            for (UUID u: users){
                if (userRepository.findAllStudents().contains(u)){
                    students.add(u);
                }
            }
            return convertIdToObject(students);
        }else {
            throw new InstanceNotFoundException();
        }
    }
    private List<User> convertIdToObject(List<UUID> uuid){
        List<User> obj = new ArrayList<>();
        for (UUID u: uuid) {
            obj.add(userRepository.findById(u).orElse(null));
        }
        return obj;
    }
}
