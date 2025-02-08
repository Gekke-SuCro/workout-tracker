package com.jaydenroeper.workouttracker.backend.security.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.jaydenroeper.workouttracker.backend.security.config.ValidationConstants.*;
import static com.jaydenroeper.workouttracker.backend.security.config.ValidationConstants.PASSWORD_LENGTH_MESSAGE;

public record LoginRequestDto(

        @NotBlank(message = "Username is required")
        @Size(min = USERNAME_MIN_LENGTH, max = USERNAME_MAX_LENGTH, message = USERNAME_LENGTH_MESSAGE)
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH, message = PASSWORD_LENGTH_MESSAGE)
        String password
) {
}
