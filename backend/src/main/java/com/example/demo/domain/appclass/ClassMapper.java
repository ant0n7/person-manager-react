package com.example.demo.domain.appclass;

import com.example.demo.domain.appclass.dto.CreateClassDTO;
import com.example.demo.domain.appclass.dto.RestrictedClassInformationDTO;
import com.example.demo.domain.appuser.User;
import com.example.demo.domain.appuser.UserRepository;
import com.example.demo.domain.appuser.dto.CreateUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    RestrictedClassInformationDTO classToRestrictedClassInformationDTO(Class appclass);
    Class restrictedClassInformationDTOToClass(RestrictedClassInformationDTO appclass);


}
