package com.jaydenroeper.workouttracker.backend.security.presentation.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDto {

    @Size(min = 2, message = "First name must have at least 2 characters")
    private String firstname;

    @Size(min = 2, message = "Last name must have at least 2 characters")
    private String lastname;

    @NotBlank(message = "Username cannot be null")
    @Size(min = 2, message = "Username must have at least 2 characters")
    private String username;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;
}