package it.italiandudes.webtrpg.web.controller;

import it.italiandudes.webtrpg.core.data.VerificationToken;
import it.italiandudes.webtrpg.core.security.enums.VerificationTokenType;
import it.italiandudes.webtrpg.core.security.repository.VerificationTokenRepository;
import it.italiandudes.webtrpg.core.security.service.WebTRPGMailService;
import jakarta.mail.MessagingException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Controller
public final class ControllerMailVerify {

    // Attributes
    @NotNull private final VerificationTokenRepository verificationTokenRepository;
    @NotNull private final WebTRPGMailService webTRPGMailService;

    // Constructors
    public ControllerMailVerify(@NotNull final VerificationTokenRepository verificationTokenRepository, @NotNull final WebTRPGMailService webTRPGMailService) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.webTRPGMailService = webTRPGMailService;
    }

    // Methods
    @GetMapping("/mail-verify")
    public String verifyMail(@ModelAttribute("token") String token) throws MessagingException {
        Optional<VerificationToken> optVerificationToken = verificationTokenRepository.findByToken(token);
        if (optVerificationToken.isPresent()) {
            VerificationToken verificationToken = optVerificationToken.get();
            if (verificationToken.getType() != VerificationTokenType.EMAIL_VERIFICATION) return "redirect:/";
            else {
                verificationToken.setVerified(true);
                verificationTokenRepository.save(verificationToken);
                webTRPGMailService.sendConfirmationVerificationMail(verificationToken.getUser().getMail());
                return "redirect:/profile";
            }
        } else return "redirect:/";
    }
}
