package org.tasks.accountsapp.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tasks.accountsapp.dto.AccountDto;
import org.tasks.accountsapp.model.AccountEntity;
import org.tasks.accountsapp.repository.AccountRepository;
import org.tasks.accountsapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean createAccount(AccountDto accountDto) {
        AccountEntity accountEntity = new AccountEntity(
                accountDto.getLogin(),
                passwordEncoder.encode(accountDto.getLogin()+accountDto.getPassword()),
                accountDto.getName(),
                accountDto.getBirthdate()
        );

        var result = accountRepository.save(accountEntity);

        return true;
    }

}
