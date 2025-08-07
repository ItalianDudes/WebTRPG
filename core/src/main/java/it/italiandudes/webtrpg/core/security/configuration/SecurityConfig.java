package it.italiandudes.webtrpg.core.security.configuration;

import it.italiandudes.webtrpg.core.security.filter.UserExistenceFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Attributes
    @NotNull private final UserExistenceFilter userExistenceFilter;

    // Bean Providers
    @Bean public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean public RequestCache requestCache() {
        return new HttpSessionRequestCache();
    }
    @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    // Constructors
    public SecurityConfig(@NotNull final UserExistenceFilter userExistenceFilter) {
        this.userExistenceFilter = userExistenceFilter;
    }

    // HTTP Routes
    @Bean
    public SecurityFilterChain filterChain(@NotNull final HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(userExistenceFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login",
                                "/register",
                                "/web/css/**",
                                "/favicon.ico",
                                "/dnd5e/css/**", // Da rimuovere prima della release
                                "/dnd5e/css_test", // Da rimuovere prima della release
                                "/fallout/css/**", // Da rimuovere prima della release
                                "/fallout/css_test" // Da rimuovere prima della release
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .sessionRegistry(sessionRegistry())
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", false)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                .redirectToHttps(Customizer.withDefaults()).build();
    }
}
