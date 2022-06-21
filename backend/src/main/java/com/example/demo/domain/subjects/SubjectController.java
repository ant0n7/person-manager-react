package com.example.demo.domain.subjects;

import com.example.demo.domain.grade.Grade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectController {
    private final SubjectService subjectService;

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/")
    public ResponseEntity<Collection<Subject>> findAllSubjects() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/")
    public ResponseEntity<Subject> addSubject(@Valid @RequestBody Subject subject) throws InstanceAlreadyExistsException {
        return new ResponseEntity<>(subjectService.saveSubject(subject), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@Parameter @PathVariable UUID id, @Valid @RequestBody Subject subject) throws InstanceNotFoundException {
        return new ResponseEntity<>(subjectService.updateSubject(id, subject), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@Parameter @PathVariable UUID id) throws InstanceNotFoundException {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
