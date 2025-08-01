package it.italiandudes.webtrpg.web.controller;

import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerWebProfile {

    @GetMapping("/profile")
    public String index(@AuthenticationPrincipal WebTRPGUserDetails userDetails, Model model) {
        assert userDetails != null; // Page protected
        model.addAttribute("user", userDetails.getUser());
        return "web/profile";
    }
}
