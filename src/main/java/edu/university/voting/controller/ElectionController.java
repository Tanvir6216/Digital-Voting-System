package edu.university.voting.controller;

import edu.university.voting.model.Candidate;
import edu.university.voting.model.Election;
import edu.university.voting.model.Position;
import edu.university.voting.model.Vote;
import edu.university.voting.repository.VoteRepository;
import edu.university.voting.service.ElectionService;
import edu.university.voting.service.VotingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller exposing endpoints for election CRUD,
 * vote casting, and results aggregation.
 */
@RestController
@RequestMapping("/api/elections")
public class ElectionController {

    private final ElectionService electionService;
    private final VotingService votingService;
    private final VoteRepository voteRepository;

    /**
     * Construct controller with required dependencies.
     *
     * @param electionService election CRUD service
     * @param votingService voting service
     * @param voteRepository vote repository (for results)
     */
    public ElectionController(ElectionService electionService,
                              VotingService votingService,
                              VoteRepository voteRepository) {
        this.electionService = electionService;
        this.votingService = votingService;
        this.voteRepository = voteRepository;
    }

    /**
     * Create a new election with nested positions and candidates.
     *
     * @param dto request body describing election structure
     * @return created election
     */
    @PostMapping
    public ResponseEntity<Election> create(@RequestBody CreateElectionDTO dto) {
        Election e = new Election(dto.name, dto.description, dto.startDate, dto.endDate);

        if (dto.positions != null) {
            for (CreatePositionDTO p : dto.positions) {
                Position pos = new Position(p.title);
                if (p.candidates != null) {
                    for (CreateCandidateDTO c : p.candidates) {
                        pos.addCandidate(new Candidate(c.name, c.party));
                    }
                }
                e.addPosition(pos);
            }
        }

        return ResponseEntity.ok(electionService.createElection(e));
    }

    /**
     * Get all elections.
     *
     * @return list of elections
     */
    @GetMapping
    public ResponseEntity<List<Election>> getAll() {
        return ResponseEntity.ok(electionService.getAll());
    }

    /**
     * Get one election by id.
     *
     * @param id election id
     * @return election
     */
    @GetMapping("/{id}")
    public ResponseEntity<Election> get(@PathVariable Long id) {
        return ResponseEntity.ok(electionService.getElection(id));
    }

    /**
     * Update election core fields (simple PUT for CRUD marks).
     *
     * @param id election id
     * @param dto update payload
     * @return updated election
     */
    @PutMapping("/{id}")
    public ResponseEntity<Election> update(@PathVariable Long id, @RequestBody UpdateElectionDTO dto) {
        Election updated = electionService.updateElection(
                id,
                dto.name,
                dto.description,
                dto.startDate,
                dto.endDate
        );
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete an election by id.
     *
     * @param id election id
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        electionService.deleteElection(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Cast a vote for a given election and candidate.
     *
     * @param id election id in URL
     * @param dto vote request
     * @return created vote
     */
    @PostMapping("/{id}/vote")
    public ResponseEntity<Vote> vote(@PathVariable Long id, @RequestBody VoteDTO dto) {
        Vote vote = votingService.castVote(dto.voterId, id, dto.candidateId);
        return ResponseEntity.ok(vote);
    }

    /**
     * Get results as candidateId -> vote count.
     *
     * @param id election id
     * @return results map
     */
    @GetMapping("/{id}/results")
    public ResponseEntity<Map<Long, Long>> results(@PathVariable Long id) {
        Map<Long, Long> counts = new HashMap<>();
        voteRepository.findByElectionId(id).forEach(v -> {
            Long cid = v.getCandidate().getId();
            counts.put(cid, counts.getOrDefault(cid, 0L) + 1);
        });
        return ResponseEntity.ok(counts);
    }

    // ===================== DTOs =====================

    /** DTO for creating an election with positions/candidates. */
    public static class CreateElectionDTO {
        /** Election name. */
        public String name;
        /** Election description. */
        public String description;
        /** Start date-time. */
        public LocalDateTime startDate;
        /** End date-time. */
        public LocalDateTime endDate;
        /** Positions in the election. */
        public CreatePositionDTO[] positions;
    }

    /** DTO for creating a position. */
    public static class CreatePositionDTO {
        /** Position title (e.g., President). */
        public String title;
        /** Candidates for the position. */
        public CreateCandidateDTO[] candidates;
    }

    /** DTO for creating a candidate. */
    public static class CreateCandidateDTO {
        /** Candidate name. */
        public String name;
        /** Candidate party/affiliation. */
        public String party;
    }

    /** DTO for updating election core fields. */
    public static class UpdateElectionDTO {
        /** New election name (nullable). */
        public String name;
        /** New description (nullable). */
        public String description;
        /** New start date (nullable). */
        public LocalDateTime startDate;
        /** New end date (nullable). */
        public LocalDateTime endDate;
    }

    /** DTO for vote casting. */
    public static class VoteDTO {
        /** Voter id. */
        public Long voterId;
        /** Candidate id. */
        public Long candidateId;
    }
}
