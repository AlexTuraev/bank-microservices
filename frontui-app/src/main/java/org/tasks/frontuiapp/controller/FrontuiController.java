package org.tasks.frontuiapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.tasks.frontuiapp.controller.utils.ControllerUtils;
import org.tasks.frontuiapp.dto.UserDto;
import org.tasks.frontuiapp.service.FrontuiService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontuiController {

    private final FrontuiService frontuiService;
    private List<String> passwordErrors = new ArrayList<>();
    private List<String> signupErrors = new ArrayList<>();

    public FrontuiController(FrontuiService frontuiService) {
        this.frontuiService = frontuiService;
    }

    @GetMapping("/main")
    public String getMain(Authentication authentication, Model model) {


        model.addAttribute("login", authentication.getName());
        model.addAttribute("passwordErrors", passwordErrors);

        return "main";
    }

    @GetMapping("/signup")
    public String getSignup(Model model) {
        model.addAttribute("errors", signupErrors);
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "confirm_password") String confirmPassword,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "birthdate") LocalDate birthdate
    ) {
        String pswError = ControllerUtils.getPasswordError(password, confirmPassword);
        if (!ControllerUtils.isValidPassword(password, confirmPassword, signupErrors)) {
            return "redirect:/signup";
        }

        UserDto userDto = new UserDto(login, password, name, birthdate);
        boolean isCreated = frontuiService.createAccount(userDto);

        return isCreated ? "redirect:/main" : "redirect:/signup";
    }

    @PostMapping("/user/{login}/editPassword")
    public String editPassword(
            @PathVariable String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "confirm_password") String confirmPassword,
            Model model
    ) {
        if (!ControllerUtils.isValidPassword(password, confirmPassword, passwordErrors)) {
            return "redirect:/main";
        }
        else {
            model.addAttribute("login", login);
            frontuiService.changePassword(login, password);
            return "redirect:/main";
        }
    }



}
