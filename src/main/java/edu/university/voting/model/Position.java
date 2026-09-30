package edu.university.voting.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a position in an election (e.g., President).
 */
@Entity
@Table(name = "positions")
public class Position {

    /** Primary key identifier. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Position title. */
    @Column(nullable = false)
    private String title;

    /**
     * The election that owns this position.
     * JSON back reference prevents recursion back to {@link Election#getPositions()}.
     */
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    /**
     * Candidates inside this position.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Candidate> candidates = new ArrayList<>();

    /** Default constructor (JPA). */
    public Position() {}

    /**
     * Construct a position.
     *
     * @param title position title
     */
    public Position(String title) {
        this.title = title;
    }

    /**
     * Add a candidate to this position.
     *
     * @param candidate candidate to add
     */
    public void addCandidate(Candidate candidate) {
        candidate.setPosition(this);
        candidates.add(candidate);
    }

    /** @return id */
    public Long getId() { return id; }

    /** @return title */
    public String getTitle() { return title; }

    /** @param title set title */
    public void setTitle(String title) { this.title = title; }

    /** @return election */
    public Election getElection() { return election; }

    /** @param election set election */
    public void setElection(Election election) { this.election = election; }

    /** @return candidates */
    public List<Candidate> getCandidates() { return candidates; }

    /** @param candidates set candidates */
    public void setCandidates(List<Candidate> candidates) { this.candidates = candidates; }
}
