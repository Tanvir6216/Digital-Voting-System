package edu.university.voting.service;

import edu.university.voting.model.Election;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service interface for managing {@link Election} entities.
 */
public interface ElectionService {

    /**
     * Persist a new election.
     *
     * @param election election entity
     * @return saved election
     */
    Election createElection(Election election);

    /**
     * Retrieve a single election by its id.
     *
     * @param id election id
     * @return election if found
     */
    Election getElection(Long id);

    /**
     * Retrieve all elections stored in the system.
     *
     * @return list of elections
     */
    List<Election> getAll();

    /**
     * Update core election fields (simple update for coursework CRUD).
     *
     * @param id election id
     * @param name new name (nullable = keep existing)
     * @param description new description (nullable = keep existing)
     * @param startDate new start date (nullable = keep existing)
     * @param endDate new end date (nullable = keep existing)
     * @return updated election
     */
    Election updateElection(Long id, String name, String description, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Delete an election by id.
     *
     * @param id election id
     * @throws IllegalArgumentException if the election does not exist
     */
    void deleteElection(Long id);
}
