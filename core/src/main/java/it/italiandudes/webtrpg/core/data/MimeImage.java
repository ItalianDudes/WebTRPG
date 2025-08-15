package it.italiandudes.webtrpg.core.data;

import it.italiandudes.webtrpg.core.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "mime_images")
@Getter
@Setter
@NoArgsConstructor // Needed for JPA
public final class MimeImage extends AuditableEntity {

    // Attributes
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, updatable = false) private String imageExtension;
    @Column(nullable = false, updatable = false) private String base64image;

    // Constructor
    @Builder
    public MimeImage(@NotNull final String imageExtension, @NotNull final String base64image) {
        this.imageExtension = imageExtension;
        this.base64image = base64image;
    }

    // ToString
    @NotNull @Override
    public String toString() {
        return "data:image/" + imageExtension + ";base64," + base64image;
    }
}
