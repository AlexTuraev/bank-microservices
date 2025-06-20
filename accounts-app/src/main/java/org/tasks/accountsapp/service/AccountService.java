package org.tasks.accountsapp.service;

import org.tasks.accountsapp.dto.AccountDto;

public interface AccountService {

    Boolean createAccount(AccountDto accountDto);

    AccountDto findByLogin(String login);
}
