package edu.university.voting.repository;

import edu.university.voting.model.Election;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Election} entities.
 * <p>
 * Provides CRUD operations and allows Spring Data JPA to generate
 * custom queries based on method names.
 * </p>
 */
public interface ElectionRepository extends JpaRepository<Election, Long> {

    // No custom methods are required at the moment,
    // but this interface can be extended easily if needed.
}
