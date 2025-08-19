package it.italiandudes.webtrpg.dnd5e.data.sheet.tabs;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import it.italiandudes.webtrpg.dnd5e.data.sheet.misc.DND5ENote;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_notes")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabNotes extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Notes
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) @JoinColumn(name = "sheet_id") private List<DND5ENote> notes = new ArrayList<>();

    // Constructors
    @Builder
    public DND5ESheetTabNotes(List<DND5ENote> notes) {
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.notes = notes != null ? notes : new ArrayList<>();
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabNotes that = (DND5ESheetTabNotes) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
