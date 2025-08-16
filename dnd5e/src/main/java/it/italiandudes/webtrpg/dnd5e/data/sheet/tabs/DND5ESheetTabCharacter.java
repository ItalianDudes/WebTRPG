package it.italiandudes.webtrpg.dnd5e.data.sheet.tabs;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.data.MimeImage;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_tab_characters")
@Check(constraints = "level >= 1 AND exp >= 0 AND max_hp >= 1 AND current_hp <= max_hp AND temp_hp >= 0 AND life_dice_faces >= 1 AND life_dice_total_amount >= 1 AND life_dice_current_amount >= 0 AND proficiency_bonus >= 0 AND inspiration_points >= 0")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
public class DND5ESheetTabCharacter extends AuditableEntity {

    // Sheet Header
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "character_name") private String characterName;
    @Min(1) @Column(nullable = false, name = "level", columnDefinition = "INTEGER DEFAULT 1") private int level = 1;
    @Column(name = "character_class") private String characterClass;
    @Column(name = "background") private String background;
    @Column(name = "race") private String race;
    @Column(name = "alignment") private String alignment;
    @Min(0) @Column(name = "exp", columnDefinition = "INTEGER DEFAULT 0") private int exp = 0;
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "character_image_id") private MimeImage characterImage;

    // Life
    @Min(1) @Transient private int calculatedMaxHP;
    @Min(1) @Column(name = "max_hp", columnDefinition = "INTEGER DEFAULT 20") private int maxHP = 20;
    @Column(name = "current_hp", columnDefinition = "INTEGER DEFAULT 20") private int currentHP = 20;
    @Min(0) @Column(name = "temp_hp", columnDefinition = "INTEGER DEFAULT 0") private int tempHP = 0;
    @Min(1) @Column(name = "life_dice_faces", columnDefinition = "INTEGER DEFAULT 20") private int lifeDiceFaces = 20;
    @Min(1) @Column(name = "life_dice_total_amount", columnDefinition = "INTEGER DEFAULT 1") private int lifeDiceTotalAmount = 1;
    @Min(0) @Column(name = "life_dice_current_amount", columnDefinition = "INTEGER DEFAULT 1") private int lifeDiceCurrentAmount = 1;

    // Proficiency Bonus and Secondary Stats
    @Min(0) @Column(name = "proficiency_bonus", columnDefinition = "INTEGER DEFAULT 2") private int proficiencyBonus = 2;
    @Column(name = "speed") private String speed;
    @Min(0) @Column(name = "inspiration_points", columnDefinition = "INTEGER DEFAULT 0") private int inspirationPoints = 0;

    // ST Against Death
    @Min(0) @Max(3) @Column(name = "success_death_st", columnDefinition = "INTEGER DEFAULT 0") private int successDeathST = 0;
    @Min(0) @Max(3) @Column(name = "fail_death_st", columnDefinition = "INTEGER DEFAULT 0") private int failDeathST = 0;

    // Character Info
    @Column(name = "personal_traits") private String personalTraits;
    @Column(name = "bonds") private String bonds;
    @Column(name = "ideals") private String ideals;
    @Column(name = "flaws") private String flaws;

    // Constructor
    @Builder
    public DND5ESheetTabCharacter(
            @Nullable final String characterName, final int level, @Nullable final String characterClass,
            @Nullable final String background, @Nullable final String race, @Nullable final String alignment,
            final int exp, @Nullable MimeImage characterImage, final int maxHP, final int currentHP, final int tempHP,
            final int lifeDiceFaces, final int lifeDiceTotalAmount, final int lifeDiceCurrentAmount, final int proficiencyBonus,
            @Nullable final String speed, final int inspirationPoints, final int successDeathST, final int failDeathST,
            @Nullable final String personalTraits, @Nullable final String bonds, @Nullable final String ideals, @Nullable final String flaws) {
        this.characterName = characterName;
        this.level = level;
        this.characterClass = characterClass;
        this.background = background;
        this.race = race;
        this.alignment = alignment;
        this.exp = exp;
        this.characterImage = characterImage;
        this.maxHP = maxHP;
        this.currentHP = currentHP;
        this.tempHP = tempHP;
        this.lifeDiceFaces = lifeDiceFaces;
        this.lifeDiceTotalAmount = lifeDiceTotalAmount;
        this.lifeDiceCurrentAmount = lifeDiceCurrentAmount;
        this.proficiencyBonus = proficiencyBonus;
        this.speed = speed;
        this.inspirationPoints = inspirationPoints;
        this.successDeathST = successDeathST;
        this.failDeathST = failDeathST;
        this.personalTraits = personalTraits;
        this.bonds = bonds;
        this.ideals = ideals;
        this.flaws = flaws;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabCharacter that = (DND5ESheetTabCharacter) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return characterName + " - " + level;
    }
}
