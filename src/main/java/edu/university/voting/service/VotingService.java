package edu.university.voting.service;

import edu.university.voting.model.Vote;

/**
 * Service interface responsible for voting operations.
 */
public interface VotingService {

    /**
     * Cast a vote for a candidate in an election on behalf of a voter.
     *
     * @param voterId     id of the voter
     * @param electionId  id of the election
     * @param candidateId id of the candidate selected
     * @return persisted {@link Vote}
     * @throws IllegalArgumentException if any entity is not found
     */
    Vote castVote(Long voterId, Long electionId, Long candidateId);
}
