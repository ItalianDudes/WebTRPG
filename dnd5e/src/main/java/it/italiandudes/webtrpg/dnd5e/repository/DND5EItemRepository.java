package it.italiandudes.webtrpg.dnd5e.repository;

import it.italiandudes.webtrpg.dnd5e.data.sheet.item.DND5EItem;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DND5EItemRepository extends JpaRepository<DND5EItem, Long> {

    // Find By
    @NotNull Optional<DND5EItem> findById(@NotNull Long id);

    // Find All By
}
