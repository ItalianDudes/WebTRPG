package it.italiandudes.webtrpg.dnd5e.data.sheet;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.data.User;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.dnd5e.data.DND5ECampaign;
import it.italiandudes.webtrpg.dnd5e.data.sheet.tabs.*;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheets")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheet extends AuditableEntity {

    // Sheet Info
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "owner_id", nullable = false) private User owner;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "campaign_id", nullable = false) private DND5ECampaign campaign;
    @Column(name = "is_dead", columnDefinition = "NOT NULL DEFAULT 0", nullable = false) private boolean isDead = false;

    // Tabs
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false) @JoinColumn(name = "tab_character_id", nullable = false) private DND5ESheetTabCharacter tabCharacter = DND5ESheetTabCharacter.builder().build();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false) @JoinColumn(name = "tab_ability_id", nullable = false) private DND5ESheetTabAbility tabAbility = DND5ESheetTabAbility.builder().build();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false) @JoinColumn(name = "tab_proficiencies_id", nullable = false) private DND5ESheetTabProficiencies tabProficiencies = DND5ESheetTabProficiencies.builder().build();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false) @JoinColumn(name = "tab_language_proficiencies_id", nullable = false) private DND5ESheetTabLanguageProficiencies tabLanguageProficiencies = DND5ESheetTabLanguageProficiencies.builder().build();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false) @JoinColumn(name = "tab_privileges_and_traits_id", nullable = false) private DND5ESheetTabPrivilegesAndTraits tabPrivilegesAndTraits = DND5ESheetTabPrivilegesAndTraits.builder().build();
    // TabEquipments (must be after TabInventory)
    // TabInventory
    // TabSpells
    // TabPhysicalDescription
    // TabStory
    // TabNotes
    // TabEffects
    // TabDiceRoller

    // Constructors
    @Builder
    public DND5ESheet(
            final User owner, final DND5ECampaign campaign, Boolean isDead,
            final DND5ESheetTabCharacter tabCharacter, final DND5ESheetTabAbility tabAbility,
            final DND5ESheetTabProficiencies tabProficiencies, final DND5ESheetTabLanguageProficiencies tabLanguageProficiencies,
            final DND5ESheetTabPrivilegesAndTraits tabPrivilegesAndTraits
    ) {
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.owner = Objects.requireNonNull(owner);
        this.campaign = Objects.requireNonNull(campaign);
        this.isDead = isDead != null ? isDead : false;
        this.tabCharacter = tabCharacter != null ? tabCharacter : DND5ESheetTabCharacter.builder().build();
        this.tabAbility = tabAbility != null ? tabAbility : DND5ESheetTabAbility.builder().build();
        this.tabProficiencies = tabProficiencies != null ? tabProficiencies : DND5ESheetTabProficiencies.builder().build();
        this.tabLanguageProficiencies = tabLanguageProficiencies != null ? tabLanguageProficiencies : DND5ESheetTabLanguageProficiencies.builder().build();
        this.tabPrivilegesAndTraits = tabPrivilegesAndTraits != null ? tabPrivilegesAndTraits : DND5ESheetTabPrivilegesAndTraits.builder().build();
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheet that = (DND5ESheet) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return tabCharacter.toString();
    }
}
