package com.jaydenroeper.workouttracker.backend.security.presentation.dto;

import jakarta.validation.constraints.*;

import static com.jaydenroeper.workouttracker.backend.security.config.AuthValidationConstants.*;

public record RegisterRequestDto(

        @NotBlank(message = "First name is required")
        @Size(min = NAME_MIN, max = NAME_MAX, message = "First name is required")
        String firstname,

        @NotBlank(message = "First name is required")
        @Size(min = NAME_MIN, max = NAME_MAX, message = "First name is required")
        String lastname,

        @NotBlank(message = "Username is required")
        @Size(min = USERNAME_MIN, max = USERNAME_MAX, message = USERNAME_MESSAGE)
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = PASSWORD_MIN, max = PASSWORD_MAX, message = PASSWORD_MESSAGE)
        String password,

        @NotBlank(message = "Confirm password is required")
        String confirmPassword
) {}
