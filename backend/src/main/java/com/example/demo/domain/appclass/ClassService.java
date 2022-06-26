package com.example.demo.domain.appclass;

import com.example.demo.domain.appclass.dto.RestrictedClassInformationDTO;
import com.example.demo.domain.subjects.Subject;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassService {
    Class saveClass(Class appclass) throws InstanceAlreadyExistsException;
    Optional<Class> findById(UUID id) throws InstanceNotFoundException;
    List<Class> findAll();
    void deleteClass(UUID id) throws InstanceNotFoundException;
    Class updateClass(UUID id, Class appclass) throws InstanceNotFoundException;

    void addUserToClass(UUID classid, UUID userid) throws InstanceNotFoundException;

    void addSubjectToClass(UUID classID, UUID subjectID) throws InstanceNotFoundException;
    List<RestrictedClassInformationDTO> findClassesByUsername(String username) throws InstanceNotFoundException;
    List<Class> findClassesBySubject(String subjectname) throws InstanceNotFoundException;

}
