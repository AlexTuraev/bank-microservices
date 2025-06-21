package org.tasks.accountsapp.service;

import org.tasks.accountsapp.dto.UserDto;
import org.tasks.accountsapp.dto.ChangePswDto;

public interface UserService {

    Boolean createAccount(UserDto accountDto);

    UserDto findByLogin(String login);

    void editPassword(ChangePswDto changePswDto);
}
