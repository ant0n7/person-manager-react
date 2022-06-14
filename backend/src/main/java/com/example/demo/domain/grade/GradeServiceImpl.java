package com.example.demo.domain.grade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GradeServiceImpl implements GradeService{
    private final GradeRepository gradeRepository;
    public Grade addGradeToUser(Grade grade){
        gradeRepository.saveAndFlush(grade);
        return grade;
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> findById(UUID id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade updateGrade(UUID id, Grade grade) throws InstanceNotFoundException {
        if (!gradeRepository.existsById(id)) throw new InstanceNotFoundException();
        grade.setId(id);
        gradeRepository.saveAndFlush(grade);
        return gradeRepository.saveAndFlush(grade);
    }

    @Override
    public void deleteGrade(UUID id) throws InstanceNotFoundException {
        if (!gradeRepository.existsById(id)) throw new InstanceNotFoundException();
        gradeRepository.deleteById(id);
    }
}
