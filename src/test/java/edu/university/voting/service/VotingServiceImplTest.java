package edu.university.voting.service;

import edu.university.voting.model.Candidate;
import edu.university.voting.model.Election;
import edu.university.voting.model.Vote;
import edu.university.voting.model.Voter;
import edu.university.voting.repository.CandidateRepository;
import edu.university.voting.repository.ElectionRepository;
import edu.university.voting.repository.UserRepository;
import edu.university.voting.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link VotingServiceImpl}.
 */
public class VotingServiceImplTest {

    private VoteRepository voteRepository;
    private UserRepository userRepository;
    private ElectionRepository electionRepository;
    private CandidateRepository candidateRepository;

    private VotingServiceImpl votingService;

    /**
     * Creates fresh mocks for each test case.
     */
    @BeforeEach
    void setUp() {
        voteRepository = mock(VoteRepository.class);
        userRepository = mock(UserRepository.class);
        electionRepository = mock(ElectionRepository.class);
        candidateRepository = mock(CandidateRepository.class);
        votingService = new VotingServiceImpl(voteRepository, userRepository, electionRepository, candidateRepository);
    }

    /**
     * Verifies a vote is saved when voter, election, and candidate exist.
     */
    @Test
    void castVote_success_savesVote() {
        Voter voter = new Voter("john", "pass", "John Doe");
        Election election = new Election();
        Candidate candidate = new Candidate("Alice", "Party A");

        when(userRepository.findById(1L)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(10L)).thenReturn(Optional.of(election));
        when(candidateRepository.findById(100L)).thenReturn(Optional.of(candidate));
        when(voteRepository.save(any(Vote.class))).thenAnswer(inv -> inv.getArgument(0));

        Vote saved = votingService.castVote(1L, 10L, 100L);

        assertNotNull(saved);
        assertEquals(candidate, saved.getCandidate());
        assertEquals(voter, saved.getVoter());
        verify(voteRepository, times(1)).save(any(Vote.class));
    }

    /**
     * Verifies an exception is thrown when voter does not exist.
     */
    @Test
    void castVote_missingVoter_throwsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> votingService.castVote(1L, 10L, 100L)
        );

        assertTrue(ex.getMessage().toLowerCase().contains("voter"));
        verify(voteRepository, never()).save(any());
    }

    /**
     * Verifies an exception is thrown when candidate does not exist.
     */
    @Test
    void castVote_missingCandidate_throwsException() {
        Voter voter = new Voter("john", "pass", "John Doe");
        Election election = new Election();

        when(userRepository.findById(1L)).thenReturn(Optional.of(voter));
        when(electionRepository.findById(10L)).thenReturn(Optional.of(election));
        when(candidateRepository.findById(100L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> votingService.castVote(1L, 10L, 100L)
        );

        assertTrue(ex.getMessage().toLowerCase().contains("candidate"));
        verify(voteRepository, never()).save(any());
    }
}
