package com.example.demo.domain.appuser;

import com.example.demo.domain.appuser.dto.CreateUserDTO;
import com.example.demo.domain.appuser.dto.SubjectUserDTO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUserDTOToUser(CreateUserDTO userDTO);
    User subjectUserDTOToUser(SubjectUserDTO subjectUserDTO);
    SubjectUserDTO userToSubjectUserDTO(User user);
}
