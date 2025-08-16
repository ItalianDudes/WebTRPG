package it.italiandudes.webtrpg.core.data;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.security.dto.RegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import it.italiandudes.webtrpg.core.security.enums.UserRole;

import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class User extends AuditableEntity {

    // Constants
    public static final String DEFAULT_USER_PROFILE = "/web/default-profile-image.svg";

    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "username", unique = true, nullable = false) private String username;
    @Column(name = "mail", unique = true, nullable = false) private String mail;
    @Column(name = "password_hash", nullable = false) private String passwordHash;
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "user_image_id") private MimeImage userImage;
    @Column(name = "role", nullable = false, columnDefinition = "INTEGER DEFAULT 0") private UserRole role = UserRole.USER;

    // Builder Constructor
    @Builder
    public User(@NotNull final String username, @NotNull final String mail, @NotNull final String passwordHash, @NotNull final UserRole role, @Nullable final MimeImage userImage) {
        this.username = username;
        this.mail = mail;
        this.passwordHash = passwordHash;
        this.role = role;
        this.userImage = userImage;
    }

    // FromDTO
    public static User fromRegisterDTO(@NotNull final RegisterDTO dto, @NotNull final PasswordEncoder encoder) {
        return User.builder()
                .username(dto.getUsername())
                .mail(dto.getMail())
                .passwordHash(encoder.encode(dto.getPlainPassword()))
                .role(UserRole.USER)
                .build();
    }

    // Methods
    @NotNull
    public String getUserImageOrDefault() {
        return userImage != null ? userImage.toString() : DEFAULT_USER_PROFILE;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return username;
    }
}
