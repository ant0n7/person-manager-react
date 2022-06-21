package com.example.demo.domain.appuser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername (String username);


    @Query(value = "select user_id from tbl_user_subjecs tus where tus.subject_id = :subjectUUID", nativeQuery = true)
    List<UUID> getStudentsBySubject(@Param("subjectUUID") UUID subjectUUID);

    @Query(value = "select user_id from tbl_user_role tur join tbl_role tr on tur.role_id = tr.id where tr.rolename = 'STUDENT'", nativeQuery = true)
    List<UUID> findAllStudents();
    
    User findByEmail(String email);
}
