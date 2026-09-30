package edu.university.voting.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing an election.
 * <p>
 * An election contains one or more {@link Position} objects.
 * This is a composition relationship (positions depend on election).
 * </p>
 */
@Entity
@Table(name = "elections")
public class Election {

    /** Primary key identifier for the election. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Human-readable election name. */
    @Column(nullable = false)
    private String name;

    /** Optional election description. */
    private String description;

    /** Date/time when voting opens. */
    private LocalDateTime startDate;

    /** Date/time when voting closes. */
    private LocalDateTime endDate;

    /**
     * Positions inside this election.
     * JSON managed reference prevents infinite recursion with {@link Position#getElection()}.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Position> positions = new ArrayList<>();

    /** Default constructor required by JPA. */
    public Election() {}

    /**
     * Construct a new election.
     *
     * @param name election name
     * @param description election description
     * @param startDate start date/time
     * @param endDate end date/time
     */
    public Election(String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Add a position to this election.
     *
     * @param position position to add
     */
    public void addPosition(Position position) {
        position.setElection(this);
        positions.add(position);
    }

    /** @return election id */
    public Long getId() { return id; }

    /** @return election name */
    public String getName() { return name; }

    /** @param name set election name */
    public void setName(String name) { this.name = name; }

    /** @return description */
    public String getDescription() { return description; }

    /** @param description set description */
    public void setDescription(String description) { this.description = description; }

    /** @return start date/time */
    public LocalDateTime getStartDate() { return startDate; }

    /** @param startDate set start date/time */
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    /** @return end date/time */
    public LocalDateTime getEndDate() { return endDate; }

    /** @param endDate set end date/time */
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    /** @return positions list */
    public List<Position> getPositions() { return positions; }

    /** @param positions set positions list */
    public void setPositions(List<Position> positions) { this.positions = positions; }
}
