package edu.university.voting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Simple health-check controller for verifying the API is running.
 * <p>
 * This endpoint is useful for Postman testing and deployment checks.
 * </p>
 */
@RestController
public class HealthController {

    /**
     * Health check endpoint.
     *
     * @return JSON response confirming the API is running
     */
    @GetMapping("/api/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "UP");
        body.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(body);
    }
}
