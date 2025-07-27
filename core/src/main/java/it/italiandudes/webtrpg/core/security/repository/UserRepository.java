package it.italiandudes.webtrpg.core.security.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.italiandudes.webtrpg.core.security.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // FindBy
    @NotNull Optional<User> findByMail(String mail);
    @NotNull Optional<User> findByUsername(String username);
}
