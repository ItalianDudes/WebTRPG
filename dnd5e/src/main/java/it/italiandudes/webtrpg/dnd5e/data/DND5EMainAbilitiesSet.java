package it.italiandudes.webtrpg.dnd5e.data;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_main_abilities_sets")
@Check(constraints = "score_strength >= 0 AND score_dexterity >= 0 AND score_constitution >= 0 AND score_intelligence >= 0 AND score_wisdom >= 0 AND score_charisma >= 0")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EMainAbilitiesSet extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Main Abilities
    @Min(0) @Column(name = "score_strength", columnDefinition = "INTEGER DEFAULT 8") private int strength = 8;
    @Min(0) @Column(name = "score_dexterity", columnDefinition = "INTEGER DEFAULT 8") private int dexterity = 8;
    @Min(0) @Column(name = "score_constitution", columnDefinition = "INTEGER DEFAULT 8") private int constitution = 8;
    @Min(0) @Column(name = "score_intelligence", columnDefinition = "INTEGER DEFAULT 8") private int intelligence = 8;
    @Min(0) @Column(name = "score_wisdom", columnDefinition = "INTEGER DEFAULT 8") private int wisdom = 8;
    @Min(0) @Column(name = "score_charisma", columnDefinition = "INTEGER DEFAULT 8") private int charisma = 8;

    // Constructors
    @Builder
    public DND5EMainAbilitiesSet(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    // Methods
    public static int getMainAbilityModifier(int score) {
        return (int) Math.floor((score-10.0)/2.0);
    }
    public int getStrengthModifier() {
        return getMainAbilityModifier(strength);
    }
    public int getDexterityModifier() {
        return getMainAbilityModifier(dexterity);
    }
    public int getConstitutionModifier() {
        return getMainAbilityModifier(constitution);
    }
    public int getIntelligenceModifier() {
        return getMainAbilityModifier(intelligence);
    }
    public int getWisdomModifier() {
        return getMainAbilityModifier(wisdom);
    }
    public int getCharismaModifier() {
        return getMainAbilityModifier(charisma);
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5EMainAbilitiesSet that = (DND5EMainAbilitiesSet) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
