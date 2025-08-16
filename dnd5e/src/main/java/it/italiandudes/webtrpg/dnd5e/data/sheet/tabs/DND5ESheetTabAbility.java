package it.italiandudes.webtrpg.dnd5e.data.sheet.tabs;

import it.italiandudes.webtrpg.dnd5e.data.DND5EAbilitiesSet;
import it.italiandudes.webtrpg.dnd5e.data.DND5EAbility;
import it.italiandudes.webtrpg.dnd5e.data.DND5EMainAbilitiesSet;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_tab_abilities")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabAbility {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Ability Scores
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "main_abilities_id", nullable = false, updatable = false) private DND5EMainAbilitiesSet mainAbilities = DND5EMainAbilitiesSet.builder().build();
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "abilities_id", nullable = false, updatable = false) private DND5EAbilitiesSet abilities = DND5EAbilitiesSet.builder().build();

    // Constructors
    @Builder
    public DND5ESheetTabAbility(@NotNull final DND5EMainAbilitiesSet mainAbilities, @NotNull final DND5EAbilitiesSet abilities) {
        this.mainAbilities = mainAbilities;
        this.abilities = abilities;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabAbility that = (DND5ESheetTabAbility) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
