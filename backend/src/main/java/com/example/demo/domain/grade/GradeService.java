package com.example.demo.domain.grade;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GradeService {
    Grade addGradeToUser(Grade grade);

    List<Grade> findAll();

    Optional<Grade> findById(UUID id);

    Grade updateGrade(UUID id, Grade grade) throws InstanceNotFoundException;

    void deleteGrade(UUID id) throws InstanceNotFoundException;
}
