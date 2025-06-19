package org.tasks.frontuiapp.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.tasks.frontuiapp.dto.UserDto;
import org.tasks.frontuiapp.service.FrontuiService;

@Controller
public class FrontuiController {

    private final FrontuiService frontuiService;

    public FrontuiController(FrontuiService frontuiService) {
        this.frontuiService = frontuiService;
    }

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "birthdate") String birthdate
    ) {
        UserDto userDto = new UserDto(login, password, name, birthdate);
        frontuiService.createAccount(userDto);

        return "main";
    }

}
