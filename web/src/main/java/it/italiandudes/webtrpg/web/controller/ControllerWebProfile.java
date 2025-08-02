package it.italiandudes.webtrpg.web.controller;

import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import it.italiandudes.webtrpg.core.security.dto.UserDataEditorDTO;
import it.italiandudes.webtrpg.core.security.service.WebTRPGUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerWebProfile {

    // Attributes
    @NotNull
    private final WebTRPGUserDetailsService webTRPGUserDetailsService;

    // Constructors
    public ControllerWebProfile(@NotNull final WebTRPGUserDetailsService webTRPGUserDetailsService) {
        this.webTRPGUserDetailsService = webTRPGUserDetailsService;
    }

    // Profile Methods
    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal WebTRPGUserDetails userDetails, Model model) {
        assert userDetails != null; // Page protected
        model.addAttribute("user", userDetails.getUser());
        return "web/profile";
    }

    // Profile Edit Methods
    @GetMapping("/profile/edit")
    public String showProfileEditor(@AuthenticationPrincipal WebTRPGUserDetails userDetails, Model model) {
        assert userDetails != null; // Page Protected
        model.addAttribute("editorDTO", new UserDataEditorDTO());
        return "web/profile_editor";
    }
    @PostMapping("/profile/edit")
    public String saveProfileEditor(@AuthenticationPrincipal WebTRPGUserDetails userDetails, @Valid @ModelAttribute("editorDTO") UserDataEditorDTO dto, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
        assert userDetails != null; // Page Protected
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return "web/profile_editor";
        }
        if (
                (dto.getUsername() == null || dto.getUsername().isBlank()) &&
                        (dto.getPlainPassword() == null || dto.getPlainPassword().isBlank())
        ) return "redirect:/profile";
        if (dto.getPlainPassword() != null && !dto.getPlainPassword().isBlank() && !dto.getPlainPassword().equals(dto.getPlainConfirmPassword())) {
            model.addAttribute("errors", "Le due password non coincidono.");
            return "web/profile_editor";
        }
        switch (webTRPGUserDetailsService.updateUserData(dto, userDetails.getUser().getMail())) {
            case SAVE_ERROR -> {
                model.addAttribute("errors", "Si è verificato un errore durante l'aggiornamento dei dati.");
                return "web/profile_editor";
            }
            case LOGOUT -> {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                    return "redirect:/";
                } else return "redirect:/profile";
            }
            default -> { // SUCCESS
                return "redirect:/profile";
            }
        }
    }
}
