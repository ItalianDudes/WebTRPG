package it.italiandudes.webtrpg.core.security.service;

import it.italiandudes.webtrpg.core.security.User;
import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import it.italiandudes.webtrpg.core.security.dto.RegisterDTO;
import it.italiandudes.webtrpg.core.security.dto.UserDataEditorDTO;
import it.italiandudes.webtrpg.core.security.enums.UserDataUpdateResult;
import it.italiandudes.webtrpg.core.security.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class WebTRPGUserDetailsService implements UserDetailsService {

    // Attributes
    @NotNull private final UserRepository userRepository;
    @NotNull private final PasswordEncoder passwordEncoder;

    // Constructor
    public WebTRPGUserDetailsService(@NotNull final UserRepository userRepository, @NotNull final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Methods
    @Override
    public UserDetails loadUserByUsername(@NotNull final String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail.trim().toLowerCase()).orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        return new WebTRPGUserDetails(user);
    }
    public boolean register(@NotNull RegisterDTO dto) {
        if (userRepository.findByMail(dto.getMail()).isPresent()) return false;
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) return false;
        userRepository.save(User.fromRegisterDTO(dto, passwordEncoder));
        return true;
    }
    @NotNull
    public UserDataUpdateResult updateUserData(@NotNull UserDataEditorDTO dto, @NotNull final String mail) {
        Optional<User> optUser = userRepository.findByMail(mail);
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (dto.getUsername() != null && !dto.getUsername().isBlank()) user.setUsername(dto.getUsername());
            boolean passwordChanged = false;
            if (dto.getPlainPassword() != null && !dto.getPlainPassword().isBlank()
            && dto.getPlainConfirmPassword() != null && !dto.getPlainConfirmPassword().isBlank()) {
                user.setPasswordHash(passwordEncoder.encode(dto.getPlainPassword()));
                passwordChanged = true;
            }
            userRepository.save(user);
            WebTRPGUserDetails newUserDetails = new WebTRPGUserDetails(user);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    newUserDetails,
                    newUserDetails.getPassword(),
                    newUserDetails.getAuthorities()
            ));
            if (passwordChanged) return UserDataUpdateResult.LOGOUT;
            else return UserDataUpdateResult.SUCCESS;
        } else return UserDataUpdateResult.SAVE_ERROR;
    }
}
