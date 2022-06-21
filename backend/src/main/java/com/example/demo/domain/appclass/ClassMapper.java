package com.example.demo.domain.appclass;

import com.example.demo.domain.appclass.dto.RestrictedClassInformationDTO;
import com.example.demo.domain.appuser.dto.CreateUserDTO;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface ClassMapper {
    Class createUserDTOToUser(CreateUserDTO userDTO);

    RestrictedClassInformationDTO classToRestrictedClassInformationDTO(Class appclass);
}
