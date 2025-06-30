package org.tasks.accountsapp.service;

import org.tasks.accountsapp.dto.ChangePswDto;
import org.tasks.accountsapp.dto.ExtUsersDto;
import org.tasks.accountsapp.dto.UserDto;

public interface UserService {

    Boolean createAccount(UserDto accountDto);

    UserDto findByLogin(String login);

    void editPassword(ChangePswDto changePswDto);

    ExtUsersDto getUsersData(String login);

//    void changeCash(CashDto cashDto);
}
