package com.jaydenroeper.workouttracker.backend.security.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/")
public class TestController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello user!");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello admin!");
    }
}
