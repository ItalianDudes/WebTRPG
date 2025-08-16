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
public final class ModelUserImageProvider {

    // Constants
    public static final String DEFAULT_USER_PROFILE = "/web/default-profile-image.svg";
    @NotNull private final UserRepository userRepository;

    // Constructors
    public ModelUserImageProvider(@NotNull final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute("userImage")
    public String addUserImageToModel(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            WebTRPGUserDetails userDetails = (WebTRPGUserDetails) authentication.getPrincipal();
            Optional<User> optLoggedUser = userRepository.findById(userDetails.getUser().getId());
            if (optLoggedUser.isPresent()) {
                User loggedUser = optLoggedUser.get();
                return loggedUser.getUserImage() != null ? loggedUser.getUserImage().toString() : DEFAULT_USER_PROFILE;
            } else return DEFAULT_USER_PROFILE;
        }
        return DEFAULT_USER_PROFILE;
    }
}
