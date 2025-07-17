package it.italiandudes.webtrpg.fallout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerFalloutCSSTest {

    @GetMapping("/css_test")
    private String css_test() {
        return "fallout/css_test";
    }
}
