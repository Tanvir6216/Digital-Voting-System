package edu.university.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application class for the Digital Voting System.
 * <p>
 * This class bootstraps the Spring context and starts the embedded
 * web server, exposing REST endpoints for the voting system.
 * </p>
 */
@SpringBootApplication
public class DigitalVotingApplication {

    /**
     * Application entry point.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        SpringApplication.run(DigitalVotingApplication.class, args);
    }
}
