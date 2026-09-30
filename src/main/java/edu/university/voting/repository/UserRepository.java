package edu.university.voting.repository;

import edu.university.voting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data repository for performing CRUD operations on {@link User} entities.
 * <p>
 * Provides built-in CRUD methods such as save(), findById(), deleteById(),
 * and can be extended with custom query methods.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by username.
     *
     * @param username unique username
     * @return optional containing the user if found
     */
    Optional<User> findByUsername(String username);
}
