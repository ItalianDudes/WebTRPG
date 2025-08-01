package it.italiandudes.webtrpg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerLogin {

    // Methods
    @GetMapping("/login")
    public String formLogin() {
        return "web/login";
    }
}
