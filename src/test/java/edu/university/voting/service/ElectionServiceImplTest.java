package edu.university.voting.service;

import edu.university.voting.model.Election;
import edu.university.voting.repository.ElectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link ElectionServiceImpl}.
 * <p>
 * Uses Mockito to isolate the service from the database layer.
 * </p>
 */
public class ElectionServiceImplTest {

    private ElectionRepository electionRepository;
    private ElectionServiceImpl electionService;

    /**
     * Creates fresh mocks for each test case.
     */
    @BeforeEach
    void setUp() {
        electionRepository = mock(ElectionRepository.class);
        electionService = new ElectionServiceImpl(electionRepository);
    }

    /**
     * Verifies deleting a missing election throws an exception and does not call deleteById.
     */
    @Test
    void deleteElection_missing_throwsException() {
        when(electionRepository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> electionService.deleteElection(99L));

        verify(electionRepository, never()).deleteById(anyLong());
    }

    /**
     * Verifies updateElection updates fields and saves the entity.
     */
    @Test
    void updateElection_success_updatesAndSaves() {
        Election existing = new Election();
        existing.setName("Old");
        existing.setDescription("Old desc");

        when(electionRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(electionRepository.save(any(Election.class))).thenAnswer(inv -> inv.getArgument(0));

        Election updated = electionService.updateElection(1L, "New", "New desc", null, null);

        assertEquals("New", updated.getName());
        assertEquals("New desc", updated.getDescription());
        verify(electionRepository, times(1)).save(existing);
    }
}
