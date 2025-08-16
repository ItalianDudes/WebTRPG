package it.italiandudes.webtrpg.core.data;

import it.italiandudes.webtrpg.core.security.enums.VerificationTokenType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "verification_tokens")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class VerificationToken {

    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "user_id", nullable = false) private User user;
    @Size(min = 36, max = 36) @Column(name = "token", unique = true, nullable = false, updatable = false) private String token;
    @Future @Column(name = "expiry_date", nullable = false, updatable = false) private LocalDateTime expiryDate;
    @Column(name = "token_type", nullable = false, updatable = false) @Enumerated(EnumType.STRING) private VerificationTokenType type;
    @Column(name = "is_verified", nullable = false) private boolean isVerified;

    // Builder Constructor
    @Builder
    public VerificationToken(@NotNull final User user, @NotNull final String token, @NotNull final VerificationTokenType type) {
        this.user = user;
        this.token = token;
        this.type = type;
        this.expiryDate = LocalDateTime.now().plusWeeks(2);
        this.isVerified = false;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        VerificationToken that = (VerificationToken) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return token;
    }
}
