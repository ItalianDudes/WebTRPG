package it.italiandudes.webtrpg.core.security.service;

import it.italiandudes.webtrpg.core.security.User;
import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import it.italiandudes.webtrpg.core.security.dto.RegisterDTO;
import it.italiandudes.webtrpg.core.security.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        User user = userRepository.findByMail(mail).orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
        return new WebTRPGUserDetails(user);
    }
    public boolean register(@NotNull RegisterDTO dto) {
        if (userRepository.findByMail(dto.getMail()).isPresent()) return false;
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) return false;
        User newUser = User.fromRegisterDTO(dto, passwordEncoder);
        userRepository.save(newUser);
        return true;
    }
}
