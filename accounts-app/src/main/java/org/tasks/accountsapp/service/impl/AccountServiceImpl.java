package org.tasks.accountsapp.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tasks.accountsapp.dto.AccountDto;
import org.tasks.accountsapp.dto.ChangePswDto;
import org.tasks.accountsapp.mapper.AccountMapper;
import org.tasks.accountsapp.model.AccountEntity;
import org.tasks.accountsapp.repository.AccountRepository;
import org.tasks.accountsapp.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Boolean createAccount(AccountDto accountDto) {
        AccountEntity accountEntity = accountMapper.mapToEntity(accountDto);

        var result = accountRepository.save(accountEntity);

        return true;
    }

    @Override
    public AccountDto findByLogin(String login) {
        List<AccountEntity> entities = accountRepository.findByLogin(login);
        if (entities.size() > 1) {
            throw new RuntimeException("More than one account found for login: " + login);
        }

        return entities.stream().map(accountMapper::mapToDto).findFirst().orElse(null);
    }

    @Override
    @Transactional
    public void editPassword(ChangePswDto changePswDto) {
        accountRepository.editPassword(changePswDto.getLogin(), changePswDto.getPasswordHash());
    }

}
