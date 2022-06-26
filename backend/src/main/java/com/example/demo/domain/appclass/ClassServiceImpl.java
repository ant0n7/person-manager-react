package com.example.demo.domain.appclass;

import com.example.demo.domain.appclass.dto.CreateClassDTO;
import com.example.demo.domain.appclass.dto.RestrictedClassInformationDTO;
import com.example.demo.domain.appuser.User;
import com.example.demo.domain.appuser.UserRepository;
import com.example.demo.domain.subjects.Subject;
import com.example.demo.domain.subjects.SubjectRepository;
import com.example.demo.domain.subjects.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final ClassMapper classMapper;
    @Override
    public Class saveClass(CreateClassDTO appclass) throws InstanceAlreadyExistsException {
        Class newClass = new Class();
        newClass.setClassname(appclass.getClassname());
        newClass.setMembers(convertUsernameToUser(appclass.getMembers()));
        newClass.setSubjects(convertSubjectnameToSubject(appclass.getSubjects()));
        if (classRepository.findByClassname(newClass.getClassname()) != null){
            throw new InstanceAlreadyExistsException("Class already exists");
        }
        return classRepository.save(newClass);
    }

    @Override
    public Optional<Class> findById(UUID id) throws InstanceNotFoundException {
        if (classRepository.existsById(id)){
            return classRepository.findById(id);
        }
        else {
            throw new InstanceNotFoundException("Subject not found");
        }
    }

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public void deleteClass(UUID id) throws InstanceNotFoundException {
        if (!classRepository.existsById(id)) throw new InstanceNotFoundException();
        classRepository.deleteById(id);
    }

    @Override
    public Class updateClass(UUID id, Class subject) throws InstanceNotFoundException {
        if (!classRepository.existsById(id)) throw new InstanceNotFoundException();
        subject.setId(id);
        return classRepository.saveAndFlush(subject);
    }

    @Override
    public void addUserToClass(UUID classID, UUID userID) throws InstanceNotFoundException {
        Class appclass;
        User user;
        if (classRepository.existsById(classID) && userRepository.existsById(userID)){
            appclass = classRepository.getById(classID);
            user = userRepository.getById(userID);
            appclass.getMembers().add(user);
        } else {
            throw new InstanceNotFoundException();
        }
    }

    @Override
    public void addSubjectToClass(UUID classID, UUID subjectID) throws InstanceNotFoundException {
        Class appclass;
        Subject subject;
        if (classRepository.existsById(classID) && subjectRepository.existsById(subjectID)){
            appclass = classRepository.getById(classID);
            subject = subjectRepository.getById(subjectID);
            appclass.getSubjects().add(subject);
        } else {
            throw new InstanceNotFoundException();
        }
    }

    @Override
    public List<RestrictedClassInformationDTO> findClassesByUsername(String username) throws InstanceNotFoundException {
        try{
            return  convertIdToRestrictedClass(classRepository.findClassesByUser(userRepository.findByUsername(username).getId()));
        } catch (Exception e){
            throw new InstanceNotFoundException("User " + username + " does not exist");
        }
    }

    @Override
    public List<Class> findClassesBySubject(String subjectname) throws InstanceNotFoundException {
        try{
            return  convertIdToClass(classRepository.findClassesByUser(subjectRepository.findBySubjectname(subjectname).getId()));
        } catch (Exception e){
            throw new InstanceNotFoundException("Subject " + subjectname + " does not exist");
        }
    }


    private List<RestrictedClassInformationDTO> convertIdToRestrictedClass(List<String> uuid){
        List<RestrictedClassInformationDTO> obj = new ArrayList<>();
        for (String u: uuid) {
            obj.add(classMapper.classToRestrictedClassInformationDTO(classRepository.findById(UUID.fromString(u)).orElse(null)));
        }
        return obj;
    }
    private List<Class> convertIdToClass(List<String> uuid){
        List<Class> obj = new ArrayList<>();
        for (String u: uuid) {
            obj.add(classRepository.findById(UUID.fromString(u)).orElse(null));
        }
        return obj;
    }

    private List<User> convertUsernameToUser(List<String> username){
        List<User> obj = new ArrayList<>();
        for (String u: username) {
            obj.add(userRepository.findByUsername(u));
        }
        return obj;
    }

    private List<Subject> convertSubjectnameToSubject(List<String> subjectname){
        List<Subject> obj = new ArrayList<>();
        for (String s: subjectname) {
            obj.add(subjectRepository.findBySubjectname(s));
        }
        return obj;
    }



}
