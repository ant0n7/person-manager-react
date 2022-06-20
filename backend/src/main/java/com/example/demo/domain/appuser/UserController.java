package com.example.demo.domain.appuser;


import com.example.demo.domain.appuser.dto.CreateUserDTO;
import com.example.demo.domain.appuser.dto.SubjectUserDTO;
import com.example.demo.domain.exceptions.InvalidEmailException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

@RestController @RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "List of all users.", description = "Get a list of all users with all their information.")
    @GetMapping("/")
    public ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Save a single user.", description = "Save a single user to the database. The API automatically encrypts the password with BCrypt and generates an UUID.")
    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDTO user)
            throws InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }


    @PreAuthorize("#username == authentication.principal.username || hasRole('TEACHER')")
    @Operation(summary = "Get an user by username.", description = "Receive a single user with all available Information by its username.")
    @GetMapping("/uname/{username}")
    public ResponseEntity<User> getByUsername(@Parameter(name = "Username", description = "Unique username of the user requested") @PathVariable String username) {
        return new ResponseEntity<>(userService.getUser(username), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Get an user by ID.", description = "Receive a single user with all available Information by its UUID.")
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@Parameter(description = "UUID of the user requested") @PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.findById(id).orElse(null), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectUserDTO> getSubjectsFromUser(@PathVariable UUID id) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.findSubjectsById(id), HttpStatus.OK);
    }

    @PreAuthorize("#username == authentication.principal.username || hasRole('TEACHER')")
    @GetMapping("/subjects/uname/{username}")
    public ResponseEntity<SubjectUserDTO> getSubjectsFromUsername(@PathVariable String username) throws InstanceNotFoundException {
        return new ResponseEntity<>(userService.findSubjectsByUsername(username), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/{subjectID}/{userID}")
    public ResponseEntity<String> addSubjectToUser( @PathVariable("userID") UUID userID, @PathVariable("subjectID") UUID subjectID) throws InstanceNotFoundException {
        userService.addSubjectToUser(userID, subjectID);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{subjectID}/{userID}")
    public ResponseEntity<String> deleteSubjectFromUser( @PathVariable("userID") UUID userID, @PathVariable("subjectID") UUID subjectID) throws InstanceNotFoundException {
        userService.deleteSubjectFromUser(userID, subjectID);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Add a role to a user.", description = "Add a single role to a single user. There won't be any " +
            "loss of roles as it just adds a role and replaces any roles.")
    @PostMapping("/{username}/role/{rolename}")
    public ResponseEntity<String> addRoleToUser(@PathVariable("username") String username, @PathVariable("rolename") String rolename) {
        try {
            userService.addRoleToUser(username, rolename);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Update a user by ID.", description = "Update a single user by its ID. Pass the whole new user in the " +
            "body and its ID in the path. If there's no user by that ID, nothing will change.")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Parameter(description = "UUID of the user to change.") @PathVariable UUID id, @Valid @RequestBody User user) throws InstanceNotFoundException, InstanceAlreadyExistsException, InvalidEmailException {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @Operation(summary = "Delete a user by ID.", description = "Delete a single user by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Parameter(description = "UUID of the user to delete.") @PathVariable UUID id) throws InstanceNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    public ResponseEntity<String> handleInstanceNotFoundException(InstanceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(InstanceAlreadyExistsException.class)
    public ResponseEntity<String> handleInstanceAlreadyExistsException(InstanceAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
