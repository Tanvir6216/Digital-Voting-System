package edu.university.voting.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Entity representing an administrative user who can manage elections.
 */
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    /**
     * Default constructor.
     */
    public Admin() {
        super();
    }

    /**
     * Full constructor for creating an admin.
     *
     * @param username username
     * @param password password
     * @param fullName full name
     */
    public Admin(String username, String password, String fullName) {
        super(username, password, fullName);
    }
}
