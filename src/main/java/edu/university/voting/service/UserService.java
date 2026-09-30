package edu.university.voting.service;

import edu.university.voting.model.Voter;

/**
 * Service interface exposing user-related operations.
 */
public interface UserService {

    /**
     * Register a new voter in the system.
     *
     * @param username username
     * @param password password
     * @param fullName full name
     * @return created {@link Voter}
     */
    Voter registerVoter(String username, String password, String fullName);

    /**
     * Find a voter by its id.
     *
     * @param id primary key
     * @return voter
     * @throws IllegalArgumentException if the user is not found or is not a voter
     */
    Voter findVoterById(Long id);
}
