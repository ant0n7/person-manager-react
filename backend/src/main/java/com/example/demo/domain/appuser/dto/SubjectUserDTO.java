package com.example.demo.domain.appuser.dto;

import com.example.demo.domain.subjects.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectUserDTO {
    private List<Subject> subjects;
}
