package it.italiandudes.webtrpg.dnd5e.data;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.dnd5e.data.enums.DND5EAbilities;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_abilities_sets")
@Getter
@SuppressWarnings("unused")
public class DND5EAbilitiesSet extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Abilities
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "athletics_id") private DND5EAbility athletics;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "acrobatics_id") private DND5EAbility acrobatics;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "stealth_id") private DND5EAbility stealth;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "sleight_of_hand_id") private DND5EAbility sleightOfHand;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "arcana_id") private DND5EAbility arcana;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "investigation_id") private DND5EAbility investigation;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "nature_id") private DND5EAbility nature;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "religion_id") private DND5EAbility religion;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "history_id") private DND5EAbility history;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "animal_handling_id") private DND5EAbility animalHandling;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "insight_id") private DND5EAbility insight;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "medicine_id") private DND5EAbility medicine;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "perception_id") private DND5EAbility perception;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "survival_id") private DND5EAbility survival;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "deception_id") private DND5EAbility deception;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "intimidation_id") private DND5EAbility intimidation;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "performance_id") private DND5EAbility performance;
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "persuasion_id") private DND5EAbility persuasion;

    // Constructors
    @Builder public DND5EAbilitiesSet(
        @NotNull DND5EAbility athletics, @NotNull DND5EAbility acrobatics, @NotNull DND5EAbility stealth,
        @NotNull DND5EAbility sleightOfHand, @NotNull DND5EAbility arcana, @NotNull DND5EAbility investigation,
        @NotNull DND5EAbility nature, @NotNull DND5EAbility religion, @NotNull DND5EAbility history,
        @NotNull DND5EAbility animalHandling, @NotNull DND5EAbility insight, @NotNull DND5EAbility medicine,
        @NotNull DND5EAbility perception, @NotNull DND5EAbility survival, @NotNull DND5EAbility deception,
        @NotNull DND5EAbility intimidation, @NotNull DND5EAbility performance, @NotNull DND5EAbility persuasion
    ) {
        this.athletics = athletics;
        this.acrobatics = acrobatics;
        this.stealth = stealth;
        this.sleightOfHand = sleightOfHand;
        this.arcana = arcana;
        this.investigation = investigation;
        this.nature = nature;
        this.religion = religion;
        this.history = history;
        this.animalHandling = animalHandling;
        this.insight = insight;
        this.medicine = medicine;
        this.perception = perception;
        this.survival = survival;
        this.deception = deception;
        this.intimidation = intimidation;
        this.performance = performance;
        this.persuasion = persuasion;
    }
    public DND5EAbilitiesSet() { // Needed for JPA
        this.athletics = new DND5EAbility(DND5EAbilities.ATHLETICS);
        this.acrobatics = new DND5EAbility(DND5EAbilities.ACROBATICS);
        this.stealth = new DND5EAbility(DND5EAbilities.STEALTH);
        this.sleightOfHand = new DND5EAbility(DND5EAbilities.SLEIGHT_OF_HAND);
        this.arcana = new DND5EAbility(DND5EAbilities.ARCANA);
        this.investigation = new DND5EAbility(DND5EAbilities.INVESTIGATION);
        this.nature = new DND5EAbility(DND5EAbilities.NATURE);
        this.religion = new DND5EAbility(DND5EAbilities.RELIGION);
        this.history = new DND5EAbility(DND5EAbilities.HISTORY);
        this.animalHandling = new DND5EAbility(DND5EAbilities.ANIMAL_HANDLING);
        this.insight = new DND5EAbility(DND5EAbilities.INSIGHT);
        this.medicine = new DND5EAbility(DND5EAbilities.MEDICINE);
        this.perception = new DND5EAbility(DND5EAbilities.PERCEPTION);
        this.survival = new DND5EAbility(DND5EAbilities.SURVIVAL);
        this.deception = new DND5EAbility(DND5EAbilities.DECEPTION);
        this.intimidation = new DND5EAbility(DND5EAbilities.INTIMIDATION);
        this.performance = new DND5EAbility(DND5EAbilities.PERFORMANCE);
        this.persuasion = new DND5EAbility(DND5EAbilities.PERSUASION);
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5EAbilitiesSet that = (DND5EAbilitiesSet) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
