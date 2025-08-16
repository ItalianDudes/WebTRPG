package it.italiandudes.webtrpg.dnd5e.data;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.data.User;
import jakarta.persistence.*;
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
@Table(name = "dnd5e_campaigns")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@Check(constraints = "max_players >= 2")
public class DND5ECampaign extends AuditableEntity {

    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false) @JoinColumn(name = "dungeon_master_id") private User dungeonMaster;
    @Column(name = "name") private String name;
    @Column(name = "max_players") @Min(2) private int maxPlayers = 2; // DM Included
    @Column(name = "description") private String description;

    // Builder Constructor
    @Builder
    public DND5ECampaign(@Nullable final User dungeonMaster, @Nullable final String name, final int maxPlayers, @Nullable final String description) {
        this.dungeonMaster = dungeonMaster;
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.description = description;
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ECampaign campaign = (DND5ECampaign) o;
        return getId() != null && Objects.equals(getId(), campaign.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    // ToString
    @Override @NotNull
    public String toString() {
        return name;
    }
}
