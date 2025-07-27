package org.tasks.accountsapp.controller;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tasks.accountsapp.dto.ChangePswDto;
import org.tasks.accountsapp.dto.ExtUsersDto;
import org.tasks.accountsapp.dto.UserDto;
import org.tasks.accountsapp.dto.commons.dto.CashDto;
import org.tasks.accountsapp.service.UserService;

@RestController
public class Oauth2Controller {

    private final UserService userService;
    private final MeterRegistry meterRegistry;

    public Oauth2Controller(UserService userService, MeterRegistry meterRegistry) {
        this.userService = userService;
        this.meterRegistry = meterRegistry;
    }

    @PostMapping("/auth")
    public ResponseEntity<UserDto> getAccount(
            @RequestBody String login
    ) {
        UserDto dto = userService.findByLogin(login);
        meterRegistry.counter("user.login", "username", login).increment();
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
        meterRegistry.counter("user.edit.password", "username", changePswDto.getLogin()).increment();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get-users-data")
    public ResponseEntity<ExtUsersDto> getUsersData(
            @RequestBody String login
    ) {
        ExtUsersDto dto = userService.getUsersData(login);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/change-cash")
    public ResponseEntity<?> changeCash(
            @RequestBody CashDto cashDto
    ) {
        userService.changeCash(cashDto);
        return ResponseEntity.ok().build();
    }

}
