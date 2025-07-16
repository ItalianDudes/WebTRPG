package it.italiandudes.webtrpg.fallout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class ControllerFalloutIndex {

    @GetMapping("/fallout")
    private String index() {
        return "fallout/index";
    }

}
