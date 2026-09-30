package edu.university.voting.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a single vote cast by a voter for a candidate
 * in a given election.
 */
@Entity
@Table(name = "votes",
       uniqueConstraints = @UniqueConstraint(
           columnNames = {"voter_id", "election_id", "candidate_id"}))
public class Vote {

    /**
     * Primary key identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Voter who cast the vote.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "voter_id")
    private Voter voter;

    /**
     * Election in which the vote was cast.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "election_id")
    private Election election;

    /**
     * Candidate chosen by the voter.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    /**
     * Timestamp when the vote was cast.
     */
    private LocalDateTime votedAt = LocalDateTime.now();

    /**
     * Default constructor.
     */
    public Vote() {
    }

    /**
     * Full constructor.
     *
     * @param voter     voter
     * @param election  election
     * @param candidate candidate
     */
    public Vote(Voter voter, Election election, Candidate candidate) {
        this.voter = voter;
        this.election = election;
        this.candidate = candidate;
    }

    /** @return id */
    public Long getId() { return id; }

    /** @return voter */
    public Voter getVoter() { return voter; }

    /** @return election */
    public Election getElection() { return election; }

    /** @return candidate */
    public Candidate getCandidate() { return candidate; }

    /** @return timestamp when vote was cast */
    public LocalDateTime getVotedAt() { return votedAt; }
}
