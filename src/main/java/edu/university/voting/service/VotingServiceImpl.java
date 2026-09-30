package edu.university.voting.service;

import edu.university.voting.model.*;
import edu.university.voting.repository.CandidateRepository;
import edu.university.voting.repository.ElectionRepository;
import edu.university.voting.repository.UserRepository;
import edu.university.voting.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of {@link VotingService}.
 * <p>
 * Encapsulates the business rules for casting a vote.
 * </p>
 */
@Service
@Transactional
public class VotingServiceImpl implements VotingService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;
    private final CandidateRepository candidateRepository;

    /**
     * Construct service with required repositories.
     *
     * @param voteRepository      vote repository
     * @param userRepository      user repository
     * @param electionRepository  election repository
     * @param candidateRepository candidate repository
     */
    public VotingServiceImpl(VoteRepository voteRepository,
                             UserRepository userRepository,
                             ElectionRepository electionRepository,
                             CandidateRepository candidateRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.electionRepository = electionRepository;
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Vote castVote(Long voterId, Long electionId, Long candidateId) {
        User u = userRepository.findById(voterId)
                .orElseThrow(() -> new IllegalArgumentException("Voter not found"));
        if (!(u instanceof Voter)) {
            throw new IllegalArgumentException("User is not a voter");
        }
        Voter voter = (Voter) u;

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new IllegalArgumentException("Election not found"));

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalArgumentException("Candidate not found"));

        Vote vote = new Vote(voter, election, candidate);
        return voteRepository.save(vote);
    }
}
