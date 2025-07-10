package org.tasks.frontuiapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tasks.commons.dto.CashDto;
import org.tasks.commons.enums.CurrencyType;
import org.tasks.frontuiapp.controller.utils.ControllerUtils;
import org.tasks.frontuiapp.dto.MainDto;
import org.tasks.frontuiapp.dto.UserDto;
import org.tasks.frontuiapp.service.FrontuiService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FrontuiController {

    private final FrontuiService frontuiService;
    private List<String> passwordErrors = new ArrayList<>();
    private List<String> signupErrors = new ArrayList<>();
    private List<CurrencyType> currencyTypes = List.of(CurrencyType.rub, CurrencyType.dollar, CurrencyType.euro);

    public FrontuiController(FrontuiService frontuiService) {
        this.frontuiService = frontuiService;
    }

    @GetMapping("/main")
    public String getMain(Authentication authentication, Model model) {

        MainDto dto = frontuiService.getMainModelData(authentication.getName());

        model.addAttribute("login", authentication.getName());
        model.addAttribute("name", dto.getName());
        model.addAttribute("birthdate", dto.getBirthdate());

        model.addAttribute("users", dto.getUsers());
        model.addAttribute("passwordErrors", passwordErrors);

        model.addAttribute("accounts", dto.getUsers().stream().filter(u -> u.getLogin().equals(authentication.getName())).findFirst().orElse(null).getBankAccounts());
        model.addAttribute("currency", currencyTypes);

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


    // Положить/снять на счет
    @PostMapping("/user/{login}/сash")
    public String cash(
            @PathVariable String login,
            @RequestParam(name = "action") String action,
            @RequestParam(name = "value") BigDecimal value,
            @RequestParam(name = "currency") CurrencyType currency,
            Model model
    ) {
        CashDto cashDto = CashDto.builder()
                .login(login)
                .action(action)
                .value(value)
                .currency(currency)
                .build();
        Boolean isSuccess = frontuiService.cash(cashDto);

//        return isSuccess ? "redirect:/main" : "redirect:/error";
        return "redirect:/main";
    }



}
