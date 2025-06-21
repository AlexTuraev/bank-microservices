package org.tasks.accountsapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.tasks.accountsapp.dto.ExtUsersDto;
import org.tasks.accountsapp.model.ExtUsersModel;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface ExtUserMapper {

    ExtUsersDto toDto(ExtUsersModel model);

}
