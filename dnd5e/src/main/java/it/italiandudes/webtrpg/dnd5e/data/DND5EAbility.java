package it.italiandudes.webtrpg.dnd5e.data;

import it.italiandudes.webtrpg.dnd5e.data.enums.DND5EAbilities;
import it.italiandudes.webtrpg.dnd5e.data.enums.DND5EProficiencyLevel;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "dnd5e_abilities")
@Getter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5EAbility {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Ability & ProficiencyLevel
    @Column(name = "ability", nullable = false, updatable = false) @Enumerated(EnumType.STRING) private DND5EAbilities ability;
    @Column(name = "proficiency_level", nullable = false) @Enumerated(EnumType.STRING) private DND5EProficiencyLevel proficiencyLevel;

    // Constructors
    @Builder
    public DND5EAbility(@NotNull final DND5EAbilities ability, @NotNull DND5EProficiencyLevel proficiencyLevel) {
        this.ability = ability;
        this.proficiencyLevel = proficiencyLevel;
    }
    public DND5EAbility(@NotNull final DND5EAbilities ability) {
        this.ability = ability;
        this.proficiencyLevel = DND5EProficiencyLevel.NONE;
    }

    // Methods
    public int getAbilityValue(int mainAbilityScore, int characterProficiencyBonus) {
        return mainAbilityScore + proficiencyLevel.getEffectiveProficiencyBonus(characterProficiencyBonus);
    }
    public void setProficiencyLevel(@NotNull DND5EProficiencyLevel newProficiencyLevel) {
        this.proficiencyLevel = newProficiencyLevel;
    }
}
