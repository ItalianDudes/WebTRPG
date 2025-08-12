package it.italiandudes.webtrpg.dnd5e.data.sheet;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.data.User;
import it.italiandudes.webtrpg.dnd5e.data.DND5ECampaign;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "dnd5e_sheets")
@Check(constraints = "level >= 1")
@Getter
@Setter
public class DND5ESheet extends AuditableEntity {

    // Sheet Info
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "owner_id") private User owner;
    @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "campaign_id") private DND5ECampaign campaign;

    // Character Name&Level
    @Column private String characterName;
    @Min(1) @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 1") private int level = 1;
}
