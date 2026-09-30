package edu.university.voting.controller;

import edu.university.voting.model.Voter;
import edu.university.voting.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing voter-related endpoints.
 */
@RestController
@RequestMapping("/api/voters")
public class VoterController {

    private final UserService userService;

    /**
     * Construct controller with the user service.
     *
     * @param userService service used to manage voters
     */
    public VoterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register a new voter.
     *
     * @param dto JSON request containing username, password and fullName
     * @return the created {@link Voter}
     */
    @PostMapping
    public ResponseEntity<Voter> register(@RequestBody VoterDTO dto) {
        Voter v = userService.registerVoter(dto.username, dto.password, dto.fullName);
        return ResponseEntity.ok(v);
    }

    /**
     * Simple DTO for voter registration.
     */
    public static class VoterDTO {
        public String username;
        public String password;
        public String fullName;
    }
}
