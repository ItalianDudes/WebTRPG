package it.italiandudes.webtrpg.web.controller;

import it.italiandudes.webtrpg.core.security.dto.RegisterDTO;
import it.italiandudes.webtrpg.core.security.service.WebTRPGUserDetailsService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerRegister {

    // Attributes
    @NotNull private final WebTRPGUserDetailsService webTRPGUserDetailsService;

    // Constructors
    public ControllerRegister(@NotNull final WebTRPGUserDetailsService webTRPGUserDetailsService) {
        this.webTRPGUserDetailsService = webTRPGUserDetailsService;
    }

    // Methods
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "web/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDTO dto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "web/register";
        }
        if (webTRPGUserDetailsService.register(dto)) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Email o username già usati");
            return "web/register";
        }
    }
}
