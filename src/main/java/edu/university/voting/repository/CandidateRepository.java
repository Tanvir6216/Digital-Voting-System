package edu.university.voting.repository;

import edu.university.voting.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations
 * on {@link Candidate} entities.
 */
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    /**
     * Find all candidates running for a specific position.
     *
     * @param positionId id of the parent position
     * @return list of candidates for the position
     */
    List<Candidate> findByPositionId(Long positionId);
}
