package org.tasks.accountsapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tasks.accountsapp.dto.UserDto;
import org.tasks.accountsapp.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", source = "passwordHash")
    UserDto mapToDto(UserEntity userEntity);

    @Mapping(target = "passwordHash", source = "password")
    UserEntity mapToEntity(UserDto accountDto);

}
