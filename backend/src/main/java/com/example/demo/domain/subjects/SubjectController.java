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
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Get all subjects.", description = "Retrieve a list of all subjects")
    @GetMapping("/")
    public ResponseEntity<Collection<Subject>> findAllSubjects() {
        return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Get a subject by ID.", description = "Retrieve a the subject with the corresponding ID")
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(subjectService.findById(id).orElse(null), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Add a subject.", description = "Add a single subject.")
    @PostMapping("/")
    public ResponseEntity<Subject> addSubject(@Valid @RequestBody Subject subject) throws InstanceAlreadyExistsException {
        return new ResponseEntity<>(subjectService.saveSubject(subject), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Update a subject by ID.", description = "Update a single subject by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@Parameter @PathVariable UUID id, @Valid @RequestBody Subject subject) throws InstanceNotFoundException {
        return new ResponseEntity<>(subjectService.updateSubject(id, subject), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Delete a subject by ID.", description = "Delete a single subject by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@Parameter @PathVariable UUID id) throws InstanceNotFoundException {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Get a subject by username.", description = "Retrieve a the subject with the corresponding ID")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Subject>> getSubjectsByUserID(@PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(subjectService.findByUserID(id), HttpStatus.OK);
    }

}
