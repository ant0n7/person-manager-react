package com.example.demo.domain.appuser;



import com.example.demo.domain.appuser.dto.CreateUserDTO;
import com.example.demo.domain.appuser.dto.SubjectUserDTO;
import com.example.demo.domain.exceptions.InvalidEmailException;
import com.example.demo.domain.subjects.Subject;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser(CreateUserDTO user) throws InstanceAlreadyExistsException, InvalidEmailException;
    void addRoleToUser(String username, String rolename);
    User getUser(String username);
    Optional<User> findById(UUID id) throws InstanceNotFoundException;
    List<User> findAll();
    User updateUser(UUID id, User user) throws InstanceNotFoundException, InvalidEmailException, InstanceAlreadyExistsException;
    void deleteUser(UUID id) throws InstanceNotFoundException;

    void addSubjectToUser(UUID userID, UUID subjectID) throws InstanceNotFoundException;

    void deleteSubjectFromUser(UUID userID, UUID subjectID) throws InstanceNotFoundException;

    SubjectUserDTO findSubjectsById(UUID id) throws InstanceNotFoundException;

    SubjectUserDTO findSubjectsByUsername(String username) throws InstanceNotFoundException;

    List<User> findUsersBySubject(UUID id) throws InstanceNotFoundException;
}

