package it.italiandudes.webtrpg.core.security;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.security.dto.RegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import it.italiandudes.webtrpg.core.security.enums.UserRole;

import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = "passwordHash")
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class User extends AuditableEntity {

    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true, nullable = false) private String username;
    @Column(unique = true, nullable = false) private String mail;
    @Column(nullable = false) private String passwordHash;
    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0") private UserRole role = UserRole.USER;

    // Builder Constructor
    @Builder
    public User(@NotNull final String username, @NotNull final String mail, @NotNull final String passwordHash, @NotNull final UserRole role) {
        this.username = username;
        this.mail = mail;
        this.passwordHash = passwordHash;
        this.role = role;
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
}
