package com.example.demo.domain.subjects;

import com.example.demo.domain.appuser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Subject findBySubjectname (String subjectName);
}
