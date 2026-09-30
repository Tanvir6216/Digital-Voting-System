package edu.university.voting.service;

import edu.university.voting.model.Election;
import edu.university.voting.repository.ElectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of {@link ElectionService}.
 * <p>
 * SRP: contains election-related business operations only.
 * </p>
 */
@Service
@Transactional
public class ElectionServiceImpl implements ElectionService {

    /** Repository for persisting and retrieving elections. */
    private final ElectionRepository electionRepository;

    /**
     * Construct service with required repository dependency.
     *
     * @param electionRepository repository for Election persistence
     */
    public ElectionServiceImpl(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    /**
     * Create and persist an election.
     *
     * @param election election entity
     * @return saved election
     */
    @Override
    public Election createElection(Election election) {
        return electionRepository.save(election);
    }

    /**
     * Retrieve a single election by id.
     *
     * @param id election id
     * @return election
     * @throws IllegalArgumentException if not found
     */
    @Override
    public Election getElection(Long id) {
        return electionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Election not found"));
    }

    /**
     * Retrieve all elections.
     *
     * @return list of elections
     */
    @Override
    public List<Election> getAll() {
        return electionRepository.findAll();
    }

    /**
     * Update core election fields (simple CRUD update).
     *
     * @param id election id
     * @param name new name (nullable)
     * @param description new description (nullable)
     * @param startDate new start date (nullable)
     * @param endDate new end date (nullable)
     * @return updated election
     * @throws IllegalArgumentException if election not found
     */
    @Override
    public Election updateElection(Long id, String name, String description,
                                   LocalDateTime startDate, LocalDateTime endDate) {

        Election existing = electionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Election not found"));

        if (name != null && !name.isBlank()) {
            existing.setName(name);
        }
        if (description != null) {
            existing.setDescription(description);
        }
        if (startDate != null) {
            existing.setStartDate(startDate);
        }
        if (endDate != null) {
            existing.setEndDate(endDate);
        }

        return electionRepository.save(existing);
    }

    /**
     * Delete an election by id.
     *
     * @param id election id
     * @throws IllegalArgumentException if the election does not exist
     */
    @Override
    public void deleteElection(Long id) {
        if (!electionRepository.existsById(id)) {
            throw new IllegalArgumentException("Election not found");
        }
        electionRepository.deleteById(id);
    }
}
