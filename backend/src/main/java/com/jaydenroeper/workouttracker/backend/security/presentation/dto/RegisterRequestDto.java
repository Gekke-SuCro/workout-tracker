package com.jaydenroeper.workouttracker.backend.security.presentation.dto;

import jakarta.validation.constraints.*;

import static com.jaydenroeper.workouttracker.backend.security.config.ValidationConstants.*;

public record RegisterRequestDto(

        @NotBlank(message = "Username is required")
        @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USERNAME_LENGTH_MESSAGE)
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = PASSWORD_LENGTH_MESSAGE)
        String password,

        @NotBlank(message = "Confirm password is required")
        @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = PASSWORD_LENGTH_MESSAGE)
        String confirmPassword
) {}
