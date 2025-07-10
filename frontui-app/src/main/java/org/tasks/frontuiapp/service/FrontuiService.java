package org.tasks.frontuiapp.service;

import org.tasks.frontuiapp.common.dto.CashDto;
import org.tasks.frontuiapp.dto.MainDto;
import org.tasks.frontuiapp.dto.UserDto;

public interface FrontuiService {

    Boolean createAccount(UserDto user);

    void changePassword(String login, String password);

    MainDto getMainModelData(String login);

    Boolean cash(CashDto cashDto);
}
