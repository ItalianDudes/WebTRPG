package it.italiandudes.webtrpg.dnd5e.data.sheet;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.data.User;
import it.italiandudes.webtrpg.dnd5e.data.DND5ECampaign;
import it.italiandudes.webtrpg.dnd5e.data.sheet.tabs.DND5ESheetTabCharacter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dnd5e_sheets")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
public class DND5ESheet extends AuditableEntity {

    // Sheet Info
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "owner_id", nullable = false) private User owner;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "campaign_id", nullable = false) private DND5ECampaign campaign;
    @Column(name = "is_dead", columnDefinition = "INTEGER DEFAULT 0", nullable = false) private boolean isDead = false;

    // Tabs
    @OneToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "tab_character_id", nullable = false, updatable = false) private DND5ESheetTabCharacter tabCharacter;
    // TabAbilities
    // TabProficienciesAndTraits
    // TabEquipments
    // TabInventory
    // TabSpells
    // TabPhysicalDescription
    // TabStory
    // TabNotes
    // TabEffects
    // TabDiceRoller
}
