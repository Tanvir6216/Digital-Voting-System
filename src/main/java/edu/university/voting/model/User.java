package edu.university.voting.model;

import jakarta.persistence.*;

/**
 * Abstract base entity representing a system user.
 * <p>
 * This class is the parent of {@link Voter} and {@link Admin}.
 * JPA SINGLE_TABLE inheritance strategy is used.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "role",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class User {

    /** Primary key identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /** Unique username used for login. */
    @Column(nullable = false, unique = true)
    protected String username;

    /** User password (plain for demo purposes). */
    @Column(nullable = false)
    protected String password;

    /** Full name of the user. */
    protected String fullName;

    /**
     * Default constructor required by JPA.
     */
    protected User() {
    }

    /**
     * Full constructor.
     *
     * @param username unique username
     * @param password password
     * @param fullName full name
     */
    protected User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    /** @return user ID */
    public Long getId() {
        return id;
    }

    /** @return username */
    public String getUsername() {
        return username;
    }

    /** @param username set username */
    public void setUsername(String username) {
        this.username = username;
    }

    /** @return password */
    public String getPassword() {
        return password;
    }

    /** @param password set password */
    public void setPassword(String password) {
        this.password = password;
    }

    /** @return full name */
    public String getFullName() {
        return fullName;
    }

    /** @param fullName set full name */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
