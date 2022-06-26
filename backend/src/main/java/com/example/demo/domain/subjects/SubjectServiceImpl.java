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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Override
    public Subject saveSubject(Subject subject) throws InstanceAlreadyExistsException {
        if (subjectRepository.findBySubjectname(subject.getSubjectname()) != null) {
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
        if (subjectRepository.existsById(id)) {
            return subjectRepository.findById(id);
        } else {
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

    @Override
    public List<Subject> findByUsername(String username) {

        List<String> classes = subjectRepository.getClassesByUser(userRepository.findByUsername(username).getId());
        List<String> subjects = new ArrayList<>();
        for (String c : classes) {
            subjects.addAll(subjectRepository.getSubjectsByClass(UUID.fromString(c)));
        }

        return convertIdToSubject(subjects);

    }

    @Override
    public List<Subject> findByUserID(UUID id) {
        List<String> classes = subjectRepository.getClassesByUser(id);
        List<String> subjects = new ArrayList<>();
        for (String c : classes) {
            subjects.addAll(subjectRepository.getSubjectsByClass(UUID.fromString(c)));
        }

        return convertIdToSubject(subjects);
    }


    private List<Subject> convertIdToSubject(List<String> subjects) {
        List<Subject> obj = new ArrayList<>();
        for (String s: subjects) {
            obj.add(subjectRepository.findById(UUID.fromString(s)).orElse(null));
        }
        return obj;
    }
}


