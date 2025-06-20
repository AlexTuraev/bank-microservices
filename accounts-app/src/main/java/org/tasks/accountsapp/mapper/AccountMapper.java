package org.tasks.accountsapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tasks.accountsapp.dto.AccountDto;
import org.tasks.accountsapp.model.AccountEntity;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "password", source = "passwordHash")
    AccountDto mapToDto(AccountEntity accountEntity);

    @Mapping(target = "passwordHash", source = "password")
    AccountEntity mapToEntity(AccountDto accountDto);

}
