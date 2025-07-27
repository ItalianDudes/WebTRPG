package it.italiandudes.webtrpg.core.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record WebTRPGUserDetails(@NotNull User user) implements UserDetails {

    // Methods
    @Override @NotNull
    public String getUsername() {
        return user.getMail();
    }
    @Override @NotNull
    public String getPassword() {
        return user.getPasswordHash();
    }
    @NotNull
    public User getUser() {
        return user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().toString()));
    }
}
