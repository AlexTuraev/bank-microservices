package org.tasks.accountsapp.service;

import org.tasks.accountsapp.dto.AccountDto;
import org.tasks.accountsapp.dto.ChangePswDto;

public interface AccountService {

    Boolean createAccount(AccountDto accountDto);

    AccountDto findByLogin(String login);

    void editPassword(ChangePswDto changePswDto);
}
