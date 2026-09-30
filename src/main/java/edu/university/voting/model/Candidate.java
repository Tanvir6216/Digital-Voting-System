package edu.university.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity representing a candidate in a position.
 */
@Entity
@Table(name = "candidates")
public class Candidate {

    /** Primary key identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Candidate name. */
    @Column(nullable = false)
    private String name;

    /** Candidate party (optional). */
    private String party;

    /**
     * The position that owns this candidate.
     * Back reference prevents recursion to {@link Position#getCandidates()}.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    /** Default constructor (JPA). */
    public Candidate() {}

    /**
     * Construct a candidate.
     *
     * @param name candidate name
     * @param party candidate party
     */
    public Candidate(String name, String party) {
        this.name = name;
        this.party = party;
    }

    /** @return id */
    public Long getId() { return id; }

    /** @return name */
    public String getName() { return name; }

    /** @param name set name */
    public void setName(String name) { this.name = name; }

    /** @return party */
    public String getParty() { return party; }

    /** @param party set party */
    public void setParty(String party) { this.party = party; }

    /** @return position */
    public Position getPosition() { return position; }

    /** @param position set position */
    public void setPosition(Position position) { this.position = position; }
}
