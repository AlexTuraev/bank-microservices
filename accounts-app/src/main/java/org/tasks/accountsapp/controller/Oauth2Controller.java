package org.tasks.accountsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tasks.accountsapp.dto.UserDto;
import org.tasks.accountsapp.dto.ChangePswDto;
import org.tasks.accountsapp.service.UserService;

@RestController
public class Oauth2Controller {

    private final UserService userService;

    public Oauth2Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<UserDto> getAccount(
            @RequestBody String login
    ) {
        UserDto dto = userService.findByLogin(login);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/account")
    public String createAccount(
            @RequestBody UserDto accountDto
    ) {
        userService.createAccount(accountDto);
        return "account has been created";
    }

    @PostMapping("/editpsw")
    public ResponseEntity<?> editPassword(
            @RequestBody ChangePswDto changePswDto
    ) {
        userService.editPassword(changePswDto);
        return ResponseEntity.ok().build();
    }

}
