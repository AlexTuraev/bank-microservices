package org.tasks.accountsapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.tasks.accountsapp.dto.BankAccountDto;
import org.tasks.accountsapp.model.BankAccountEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankAccountMapper {

    BankAccountDto toDto(BankAccountEntity model);

}
