package edu.university.voting.repository;

import edu.university.voting.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for accessing {@link Position} entities.
 * <p>
 * Provides access to positions within elections and allows
 * retrieval of all positions belonging to a specific election.
 * </p>
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

    /**
     * Retrieve all positions associated with a given election.
     *
     * @param electionId id of the election
     * @return list of positions belonging to the election
     */
    List<Position> findByElectionId(Long electionId);
}
