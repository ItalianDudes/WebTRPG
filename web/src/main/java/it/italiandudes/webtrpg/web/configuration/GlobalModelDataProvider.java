package it.italiandudes.webtrpg.web.configuration;

import it.italiandudes.webtrpg.core.data.User;
import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import it.italiandudes.webtrpg.core.security.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public final class GlobalModelDataProvider {

    // Attributes
    @NotNull private final UserRepository userRepository;

    // Constructors
    public GlobalModelDataProvider(@NotNull final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("loggedUser")
    public User addUserToModel(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            WebTRPGUserDetails userDetails = (WebTRPGUserDetails) authentication.getPrincipal();
            Optional<User> optLoggedUser = userRepository.findById(userDetails.getUser().getId());
            return optLoggedUser.orElse(null);
        }
        return null;
    }
}
