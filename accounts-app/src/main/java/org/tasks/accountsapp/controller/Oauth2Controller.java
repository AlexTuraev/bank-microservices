package org.tasks.accountsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tasks.accountsapp.dto.UserDto;

@RestController
public class Oauth2Controller {

    @GetMapping("/account")
    public String getAccount() {
        return "account";
    }

    @PostMapping("/account")
    public String createAccount(
            @RequestBody UserDto userDto
    ) {
        return "account has been created";
    }

}
