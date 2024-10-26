package com.elotech.viniciuspdionizio.library_api.model.mapper;

import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserRequestDTO;
import com.elotech.viniciuspdionizio.library_api.model.dto.user.UserResponseDTO;
import com.elotech.viniciuspdionizio.library_api.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserEntity toEntity(UserRequestDTO dto);

    UserResponseDTO toDTO(UserEntity entity);

}
