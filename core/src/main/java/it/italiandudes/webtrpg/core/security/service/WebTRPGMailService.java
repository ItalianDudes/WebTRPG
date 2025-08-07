package it.italiandudes.webtrpg.core.security.service;

import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Service
public final class WebTRPGMailService {

    // Attributes
    @NotNull private final JavaMailSender MAIL_SENDER;
    @Value("${WEBTRPG_MAIL}") private String MAIL;
    private InternetAddress NAME_MAIL = null;
    private static final String VERIFY_SUBJECT = "WebTRPG Mail Verification";
    private static final String VERIFY_URL_TEMPLATE = "https://127.0.0.1/mail-verify?token=";

    // Constructors
    public WebTRPGMailService(@NotNull final JavaMailSender mailSender) {
        this.MAIL_SENDER = mailSender;
    }

    // Methods
    public void sendConfirmationVerificationMail(@NotNull final String targetMail) throws MessagingException {
        String mailContent = "<h1>Welcome to WebTRPG!</h1><p>You're mail has been successfully verified!</p>";
        sendMail(targetMail, VERIFY_SUBJECT, mailContent, true);
    }
    public void sendVerificationMail(@NotNull final String targetMail, @NotNull final String token) throws MessagingException {
        String verifyURL = VERIFY_URL_TEMPLATE + token;
        String mailContent = "<p>Click <a href=\"" + verifyURL + "\">here</a> to verify the mail.</p><br/><p>Warning: the link will expire in 2 weeks.</p><br/><p>The WebTRPG account will be deleted if the mail verification expires.</p>";
        sendMail(targetMail, VERIFY_SUBJECT, mailContent, true);
    }
    public void sendMail(@NotNull final String targetMail, @NotNull final String subject, @NotNull final String body, final boolean isBodyHtml) throws MessagingException {
        MimeMessage message = MAIL_SENDER.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(targetMail);
        helper.setSubject(subject);
        helper.setText(body, isBodyHtml);
        try {
            if (NAME_MAIL == null) NAME_MAIL = new InternetAddress(MAIL, "WebTRPG");
        } catch (UnsupportedEncodingException e) {
            WebTRPGLogger.getLogger().error("An error has occurred while creating the WebTRPG Mail InternetAddress.", e);
        }
        helper.setFrom(NAME_MAIL);
        MAIL_SENDER.send(message);
    }

    // Static
    @NotNull
    public static String generateMailVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
