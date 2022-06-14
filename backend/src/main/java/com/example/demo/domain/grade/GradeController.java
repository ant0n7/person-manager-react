package com.example.demo.domain.grade;

import com.example.demo.domain.appuser.User;
import com.example.demo.domain.appuser.dto.CreateUserDTO;
import com.example.demo.domain.exceptions.InvalidEmailException;
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

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/")
    public ResponseEntity<Grade> addGrade(@Valid @RequestBody Grade grade) {
        return new ResponseEntity<>(gradeService.addGradeToUser(grade), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Collection<Grade>> findAll() {
        return new ResponseEntity<>(gradeService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/byId/{id}")
    public ResponseEntity<Grade> getGradeById(@Parameter(description = "UUID of the user requested") @PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(gradeService.findById(id).orElse(null), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@Parameter @PathVariable UUID id, @Valid @RequestBody Grade grade) throws InstanceNotFoundException {
        return new ResponseEntity<>(gradeService.updateGrade(id, grade), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Delete a user by ID.", description = "Delete a single user by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrade(@Parameter(description = "UUID of the user to delete.") @PathVariable UUID id) throws InstanceNotFoundException {
        gradeService.deleteGrade(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
