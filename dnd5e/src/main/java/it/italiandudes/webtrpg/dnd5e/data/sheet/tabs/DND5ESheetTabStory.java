package it.italiandudes.webtrpg.dnd5e.data.sheet.tabs;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import it.italiandudes.webtrpg.core.data.MimeImage;
import it.italiandudes.webtrpg.core.logging.WebTRPGLogger;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Table(name = "dnd5e_sheet_tabs_stories")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
@SuppressWarnings("unused")
public class DND5ESheetTabStory extends AuditableEntity {

    // Entity ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    // Sheet Header
    @Column(name = "story", nullable = false) private String story = "";
    @Column(name = "cult", nullable = false) private String cult = "";
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "cult_image_id") private MimeImage cultImage = null;
    @Column(name = "allies_and_organizations", nullable = false) private String alliesAndOrganizations = "";

    // Constructor
    @Builder
    public DND5ESheetTabStory(String story, String cult, MimeImage cultImage, String alliesAndOrganizations) {
        WebTRPGLogger.getLogger().debug(this.getClass().getName());
        this.story = story != null ? story : "";
        this.cult = cult != null ? cult : "";
        this.cultImage = cultImage;
        this.alliesAndOrganizations = alliesAndOrganizations != null ? alliesAndOrganizations : "";
    }

    // JPA Equals&HashCode
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DND5ESheetTabStory that = (DND5ESheetTabStory) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
