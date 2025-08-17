package it.italiandudes.webtrpg.dnd5e.data.sheet.tabs;

import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.dnd5e.data.enums.DND5EProficiencyLevel;
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
@Table(name = "dnd5e_tab_abilities")
@Getter
@Setter
@Check(constraints = "score_strength >= 0 AND score_dexterity >= 0 AND score_constitution >= 0 AND score_intelligence >= 0 AND score_wisdom >= 0 AND score_charisma >= 0")
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabAbility {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Main Abilities
    @Min(0) @Column(name = "score_strength", columnDefinition = "NOT NULL DEFAULT 8", nullable = false) private int strength = 8;
    @Min(0) @Column(name = "score_dexterity", columnDefinition = "NOT NULL DEFAULT 8", nullable = false) private int dexterity = 8;
    @Min(0) @Column(name = "score_constitution", columnDefinition = "NOT NULL DEFAULT 8", nullable = false) private int constitution = 8;
    @Min(0) @Column(name = "score_intelligence", columnDefinition = "NOT NULL DEFAULT 8", nullable = false) private int intelligence = 8;
    @Min(0) @Column(name = "score_wisdom", columnDefinition = "NOT NULL DEFAULT 8", nullable = false) private int wisdom = 8;
    @Min(0) @Column(name = "score_charisma", columnDefinition = "NOT NULL DEFAULT 8", nullable = false) private int charisma = 8;

    // Saving Throws Proficiencies
    @Column(name = "st_strength", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel stStrength = DND5EProficiencyLevel.NONE;
    @Column(name = "st_dexterity", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel stDexterity = DND5EProficiencyLevel.NONE;
    @Column(name = "st_constitution", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel stConstitution = DND5EProficiencyLevel.NONE;
    @Column(name = "st_intelligence", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel stIntelligence = DND5EProficiencyLevel.NONE;
    @Column(name = "st_wisdom", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel stWisdom = DND5EProficiencyLevel.NONE;
    @Column(name = "st_charisma", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel stCharisma = DND5EProficiencyLevel.NONE;
    
    // Ability Proficiencies
    @Column(name = "athletics", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel athletics = DND5EProficiencyLevel.NONE;
    @Column(name = "acrobatics", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel acrobatics = DND5EProficiencyLevel.NONE;
    @Column(name = "stealth", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel stealth = DND5EProficiencyLevel.NONE;
    @Column(name = "sleight_of_hand", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel sleightOfHand = DND5EProficiencyLevel.NONE;
    @Column(name = "arcana", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel arcana = DND5EProficiencyLevel.NONE;
    @Column(name = "investigation", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel investigation = DND5EProficiencyLevel.NONE;
    @Column(name = "nature", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel nature = DND5EProficiencyLevel.NONE;
    @Column(name = "religion", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel religion = DND5EProficiencyLevel.NONE;
    @Column(name = "history", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel history = DND5EProficiencyLevel.NONE;
    @Column(name = "animal_handling", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel animalHandling = DND5EProficiencyLevel.NONE;
    @Column(name = "insight", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel insight = DND5EProficiencyLevel.NONE;
    @Column(name = "medicine", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel medicine = DND5EProficiencyLevel.NONE;
    @Column(name = "perception", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel perception = DND5EProficiencyLevel.NONE;
    @Column(name = "survival", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel survival = DND5EProficiencyLevel.NONE;
    @Column(name = "deception", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel deception = DND5EProficiencyLevel.NONE;
    @Column(name = "intimidation", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel intimidation = DND5EProficiencyLevel.NONE;
    @Column(name = "performance", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel performance = DND5EProficiencyLevel.NONE;
    @Column(name = "persuasion", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel persuasion = DND5EProficiencyLevel.NONE;

    // Constructors
    @Builder
    public DND5ESheetTabAbility(
            Integer strength, Integer dexterity, Integer constitution, Integer intelligence, Integer wisdom, Integer charisma,
            DND5EProficiencyLevel stStrength, DND5EProficiencyLevel stDexterity, DND5EProficiencyLevel stConstitution,
            DND5EProficiencyLevel stIntelligence, DND5EProficiencyLevel stWisdom, DND5EProficiencyLevel stCharisma,
            DND5EProficiencyLevel athletics, DND5EProficiencyLevel acrobatics, DND5EProficiencyLevel stealth,
            DND5EProficiencyLevel sleightOfHand, DND5EProficiencyLevel arcana, DND5EProficiencyLevel investigation,
            DND5EProficiencyLevel nature, DND5EProficiencyLevel religion, DND5EProficiencyLevel history,
            DND5EProficiencyLevel animalHandling, DND5EProficiencyLevel insight, DND5EProficiencyLevel medicine,
            DND5EProficiencyLevel perception, DND5EProficiencyLevel survival, DND5EProficiencyLevel deception,
            DND5EProficiencyLevel intimidation, DND5EProficiencyLevel performance, DND5EProficiencyLevel persuasion
    ) {
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.strength = strength != null ? strength : 8;
        this.dexterity = dexterity != null ? dexterity : 8;
        this.constitution = constitution != null ? constitution : 8;
        this.intelligence = intelligence != null ? intelligence : 8;
        this.wisdom = wisdom != null ? wisdom : 8;
        this.charisma = charisma != null ? charisma : 8;

        this.stStrength = stStrength != null ? stStrength : DND5EProficiencyLevel.NONE;
        this.stDexterity = stDexterity != null ? stDexterity : DND5EProficiencyLevel.NONE;
        this.stConstitution = stConstitution != null ? stConstitution : DND5EProficiencyLevel.NONE;
        this.stIntelligence = stIntelligence != null ? stIntelligence : DND5EProficiencyLevel.NONE;
        this.stWisdom = stWisdom != null ? stWisdom : DND5EProficiencyLevel.NONE;
        this.stCharisma = stCharisma != null ? stCharisma : DND5EProficiencyLevel.NONE;

        this.athletics = athletics != null ? athletics : DND5EProficiencyLevel.NONE;
        this.acrobatics = acrobatics != null ? acrobatics : DND5EProficiencyLevel.NONE;
        this.stealth = stealth != null ? stealth : DND5EProficiencyLevel.NONE;
        this.sleightOfHand = sleightOfHand != null ? sleightOfHand : DND5EProficiencyLevel.NONE;
        this.arcana = arcana != null ? arcana : DND5EProficiencyLevel.NONE;
        this.investigation = investigation != null ? investigation : DND5EProficiencyLevel.NONE;
        this.nature = nature != null ? nature : DND5EProficiencyLevel.NONE;
        this.religion = religion != null ? religion : DND5EProficiencyLevel.NONE;
        this.history = history != null ? history : DND5EProficiencyLevel.NONE;
        this.animalHandling = animalHandling != null ? animalHandling : DND5EProficiencyLevel.NONE;
        this.insight = insight != null ? insight : DND5EProficiencyLevel.NONE;
        this.medicine = medicine != null ? medicine : DND5EProficiencyLevel.NONE;
        this.perception = perception != null ? perception : DND5EProficiencyLevel.NONE;
        this.survival = survival != null ? survival : DND5EProficiencyLevel.NONE;
        this.deception = deception != null ? deception : DND5EProficiencyLevel.NONE;
        this.intimidation = intimidation != null ? intimidation : DND5EProficiencyLevel.NONE;
        this.performance = performance != null ? performance : DND5EProficiencyLevel.NONE;
        this.persuasion = persuasion != null ? persuasion : DND5EProficiencyLevel.NONE;
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
        DND5ESheetTabAbility that = (DND5ESheetTabAbility) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
