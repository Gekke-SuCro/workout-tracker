package com.jaydenroeper.workouttracker.backend.security.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaydenroeper.workouttracker.backend.security.application.AuthService;
import com.jaydenroeper.workouttracker.backend.security.application.dto.AuthResponseDto;
import com.jaydenroeper.workouttracker.backend.security.data.exception.UsernameNotUniqueException;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;

import jakarta.validation.Valid;

import com.jaydenroeper.workouttracker.backend.security.domain.Users;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
@Validated
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

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        try {
            Users newUser = authService.register(registerRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User registered successfully!");
        } catch (UsernameNotUniqueException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while registering the user.");
        }
    }
}
