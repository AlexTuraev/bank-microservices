package org.tasks.accountsapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tasks.accountsapp.dto.AccountDto;
import org.tasks.accountsapp.dto.ChangePswDto;
import org.tasks.accountsapp.service.AccountService;

@RestController
public class Oauth2Controller {

    private final AccountService accountService;

    public Oauth2Controller(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AccountDto> getAccount(
            @RequestBody String login
    ) {
        AccountDto dto = accountService.findByLogin(login);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/account")
    public String createAccount(
            @RequestBody AccountDto accountDto
    ) {
        accountService.createAccount(accountDto);
        return "account has been created";
    }

    @PostMapping("/editpsw")
    public ResponseEntity<?> editPassword(
            @RequestBody ChangePswDto changePswDto
    ) {
        accountService.editPassword(changePswDto);
        return ResponseEntity.ok().build();
    }

}
