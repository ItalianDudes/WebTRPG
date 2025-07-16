package it.italiandudes.webtrpg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ControllerWebIndex {

    @GetMapping
    public String index() {
        return "web/index";
    }
}
