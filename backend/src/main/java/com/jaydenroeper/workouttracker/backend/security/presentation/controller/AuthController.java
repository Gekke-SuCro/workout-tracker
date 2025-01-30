package com.jaydenroeper.workouttracker.backend.security.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydenroeper.workouttracker.backend.security.application.AuthService;
import com.jaydenroeper.workouttracker.backend.security.application.dto.AuthResponseDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            String token = authService.verify(loginRequestDto);
            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setAccesToken(token);

            return ResponseEntity.ok(authResponseDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred.");
        }
    }
}
