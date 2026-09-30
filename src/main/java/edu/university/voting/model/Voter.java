package edu.university.voting.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Entity representing a registered voter.
 * <p>
 * This class extends {@link User} and is identified
 * by the discriminator value {@code VOTER}.
 */
@Entity
@DiscriminatorValue("VOTER")
public class Voter extends User {

    /**
     * Default constructor required by JPA.
     */
    public Voter() {
        super();
    }

    /**
     * Full constructor for creating a voter.
     *
     * @param username username
     * @param password password
     * @param fullName full name
     */
    public Voter(String username, String password, String fullName) {
        super(username, password, fullName);
    }
}
