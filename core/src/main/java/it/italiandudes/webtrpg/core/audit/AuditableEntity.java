package it.italiandudes.webtrpg.core.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass @SuppressWarnings("unused")
public abstract class AuditableEntity {
    @Column(updatable = false) @CreationTimestamp protected LocalDateTime createdAt;
    @Column @UpdateTimestamp protected LocalDateTime updatedAt;
}
