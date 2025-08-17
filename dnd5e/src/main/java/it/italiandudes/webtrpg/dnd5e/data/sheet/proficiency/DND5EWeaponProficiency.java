package it.italiandudes.webtrpg.dnd5e.data.sheet.proficiency;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.dnd5e.data.sheet.enums.DND5EProficiencyLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_weapon_proficiencies")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EWeaponProficiency extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Attributes
    @Column(name = "name", nullable = false) private String name = "";
    @Column(name = "proficiency_level", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyLevel = DND5EProficiencyLevel.NONE;

    // Constructors
    @Builder
    public DND5EWeaponProficiency(String name, DND5EProficiencyLevel proficiencyLevel) {
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.name = name != null ? name : "";
        this.proficiencyLevel = proficiencyLevel != null ? proficiencyLevel : DND5EProficiencyLevel.NONE;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5EWeaponProficiency that = (DND5EWeaponProficiency) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return "\"" + name + "\" - " + proficiencyLevel.name();
    }
}
