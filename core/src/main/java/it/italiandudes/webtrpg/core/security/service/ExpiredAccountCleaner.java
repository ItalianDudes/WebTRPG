package it.italiandudes.webtrpg.core.security.service;

import it.italiandudes.webtrpg.core.data.User;
import it.italiandudes.webtrpg.core.data.VerificationToken;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.core.security.WebTRPGUserDetails;
import it.italiandudes.webtrpg.core.security.enums.VerificationTokenType;
import it.italiandudes.webtrpg.core.security.repository.UserRepository;
import it.italiandudes.webtrpg.core.security.repository.VerificationTokenRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpiredAccountCleaner {

    // Attributes
    @NotNull private final SessionRegistry sessionRegistry;
    @NotNull private final VerificationTokenRepository verificationTokenRepository;
    @NotNull private final UserRepository userRepository;

    // Constructors
    public ExpiredAccountCleaner(@NotNull final SessionRegistry sessionRegistry, @NotNull final VerificationTokenRepository verificationTokenRepository, @NotNull final UserRepository userRepository) {
        this.sessionRegistry = sessionRegistry;
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    // Methods
    @Scheduled(cron = "0 * * * * *") // Ogni minuto
    public void cleanExpiredUnverifiedAccounts() {
        List<VerificationToken> expiredTokens = verificationTokenRepository.findByIsVerifiedFalseAndExpiryDateBefore(LocalDateTime.now())
                .stream().filter(verificationToken -> verificationToken.getType() == VerificationTokenType.EMAIL_VERIFICATION).toList();
        for (VerificationToken token : expiredTokens) {
            User user = token.getUser();
            invalidateUserSession(user);
            verificationTokenRepository.delete(token);
            userRepository.delete(user);
            WebTRPGLogger.getLogger().info("{}\" because of email verification expired.", "Deleted account \"" + user.getUsername());
        }
        WebTRPGLogger.getLogger().info("Total deleted account: {}", expiredTokens.size());
    }
    private void invalidateUserSession(@NotNull final User user) {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        for (Object principal : principals) {
            if (principal instanceof WebTRPGUserDetails userDetails && userDetails.getUser().getMail().equals(user.getMail())) {
                List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
                for (SessionInformation session : sessions) {
                    session.expireNow();
                }
            }
        }
    }
}
