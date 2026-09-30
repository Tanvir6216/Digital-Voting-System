package edu.university.voting.repository;

import edu.university.voting.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for managing {@link Vote} records.
 * <p>
 * Provides methods for retrieving votes cast in an election
 * and supports custom query generation by Spring Data JPA.
 * </p>
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {

    /**
     * Retrieve all votes belonging to a specific election.
     *
     * @param electionId id of the election
     * @return list of votes for that election
     */
    List<Vote> findByElectionId(Long electionId);
}
