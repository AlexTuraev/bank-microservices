package org.tasks.frontuiapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontuiController {

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "signup";
    }

}
