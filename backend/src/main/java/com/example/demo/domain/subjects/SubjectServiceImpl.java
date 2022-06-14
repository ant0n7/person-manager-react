package com.example.demo.domain.subjects;

import com.example.demo.domain.appuser.User;
import com.example.demo.domain.appuser.UserRepository;
import com.example.demo.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    @Override
    public Subject saveSubject(Subject subject) throws InstanceAlreadyExistsException {
        if (subjectRepository.findBySubjectname(subject.getSubjectname()) != null){
            throw new InstanceAlreadyExistsException("Subject already exists");
        }
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubject(String subjectName) {
        return subjectRepository.findBySubjectname(subjectName);
    }

    @Override
    public Optional<Subject> findById(UUID id) throws InstanceNotFoundException {
        if (subjectRepository.existsById(id)){
            return subjectRepository.findById(id);
        }
        else {
            throw new InstanceNotFoundException("Subject not found");
        }
    }

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public void deleteSubject(UUID id) throws InstanceNotFoundException {
        if (!subjectRepository.existsById(id)) throw new InstanceNotFoundException();
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject updateSubject(UUID id, Subject subject) throws InstanceNotFoundException {
        if (!subjectRepository.existsById(id)) throw new InstanceNotFoundException();
        subject.setId(id);
        return subjectRepository.saveAndFlush(subject);
    }


}
