package com.example.demo.domain.appuser;

import com.example.demo.domain.grade.Grade;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.subjects.Subject;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Entity(name="tbl_user")
//from lombok
@Getter@Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Size(min = 3, max = 16) @NotNull
    private String username;
    @Email @NotNull
    private String email;
    @Size(min = 8, max = 128) @NotNull
    private String password;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany()
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "tbl_user_subject",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id", referencedColumnName = "id"))
    private List<Subject> subjects;

    @OneToMany
    @JoinColumn(name = "grades_id", referencedColumnName = "id")
    private List<Grade> grades;
}
