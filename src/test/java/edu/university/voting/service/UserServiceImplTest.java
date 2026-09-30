package edu.university.voting.service;

import edu.university.voting.model.User;
import edu.university.voting.model.Voter;
import edu.university.voting.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link UserServiceImpl}.
 */
public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserServiceImpl userService;

    /**
     * Creates fresh mocks for each test case.
     */
    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    /**
     * Verifies registering a voter returns the saved voter.
     */
    @Test
    void registerVoter_success() {
        when(userRepository.save(any(Voter.class))).thenAnswer(inv -> inv.getArgument(0));

        Voter voter = userService.registerVoter("alice", "pass", "Alice A");

        assertNotNull(voter);
        assertEquals("alice", voter.getUsername());
        verify(userRepository, times(1)).save(any(Voter.class));
    }

    /**
     * Verifies findVoterById throws when user is missing.
     */
    @Test
    void findVoterById_missingUser_throws() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.findVoterById(1L));
    }

    /**
     * Verifies findVoterById throws when the id belongs to a non-voter user.
     */
    @Test
    void findVoterById_notVoter_throws() {
        User nonVoterUser = mock(User.class);
        when(userRepository.findById(2L)).thenReturn(Optional.of(nonVoterUser));

        assertThrows(IllegalArgumentException.class, () -> userService.findVoterById(2L));
    }
}
