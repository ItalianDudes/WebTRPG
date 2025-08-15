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
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name = "dnd5e_tab_characters")
@Check(constraints = "level >= 1 AND exp >= 0 AND maxHP >= 1 AND currentHP <= maxHP AND tempHP >= 0 AND lifeDiceFaces >= 1 AND lifeDiceTotalAmount >= 1 AND lifeDiceCurrentAmount >= 0 AND proficiencyBonus >= 0 AND inspirationPoints >= 0")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
public class DND5ESheetTabCharacter extends AuditableEntity {

    // Sheet Header
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column private String characterName;
    @Min(1) @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 1") private int level = 1;
    @Column private String characterClass;
    @Column private String background;
    @Column private String race;
    @Column private String alignment;
    @Min(0) @Column(columnDefinition = "INTEGER DEFAULT 0") private int exp = 0;
    @OneToOne(fetch = FetchType.EAGER) @JoinColumn(name = "character_image_id") private MimeImage characterImage;

    // Life
    @Min(1) private int calculatedMaxHP;
    @Min(1) @Column(columnDefinition = "INTEGER DEFAULT 20") private int maxHP = 20;
    @Column(columnDefinition = "INTEGER DEFAULT 20") private int currentHP = 20;
    @Min(0) @Column(columnDefinition = "INTEGER DEFAULT 0") private int tempHP = 0;
    @Min(1) @Column(columnDefinition = "INTEGER DEFAULT 20") private int lifeDiceFaces = 20;
    @Min(1) @Column(columnDefinition = "INTEGER DEFAULT 1") private int lifeDiceTotalAmount = 1;
    @Min(0) @Column(columnDefinition = "INTEGER DEFAULT 1") private int lifeDiceCurrentAmount = 1;

    // Proficiency Bonus and Secondary Stats
    @Min(0) @Column(columnDefinition = "INTEGER DEFAULT 2") private int proficiencyBonus = 2;
    @Column private String speed;
    @Min(0) @Column(columnDefinition = "INTEGER DEFAULT 0") private int inspirationPoints = 0;

    // ST Against Death
    @Min(0) @Max(3) @Column(columnDefinition = "INTEGER DEFAULT 0") private int successDeathST = 0;
    @Min(0) @Max(3) @Column(columnDefinition = "INTEGER DEFAULT 0") private int failDeathST = 0;

    // Character Info
    @Column private String personalTraits;
    @Column private String bonds;
    @Column private String ideals;
    @Column private String flaws;

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
}
