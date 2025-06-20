package org.tasks.frontuiapp.service;

import org.tasks.frontuiapp.dto.UserDto;

public interface FrontuiService {

    Boolean createAccount(UserDto user);

    void changePassword(String login, String password);
}
