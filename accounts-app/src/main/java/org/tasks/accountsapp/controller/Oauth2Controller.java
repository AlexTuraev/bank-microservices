package org.tasks.accountsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

    @GetMapping("/account")
    public String getAccount() {
        return "account";
    }

}
