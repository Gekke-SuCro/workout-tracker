package com.jaydenroeper.workouttracker.backend.security.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import static com.jaydenroeper.workouttracker.backend.security.validation.ValidationConstants.*;

@Data
public class RegisterRequestDto {

    @NotBlank(message = "First name is required")
    @Size(min = NAME_MIN, max = NAME_MAX,
            message = "First name must be between " + NAME_MIN + " and " + NAME_MAX + " characters")
    @Pattern(regexp = NAME_REGEX, message = NAME_MESSAGE)
    private String firstname;

    @NotBlank(message = "Last name is required")
    @Size(min = NAME_MIN, max = NAME_MAX,
            message = "Last name must be between " + NAME_MIN + " and " + NAME_MAX + " characters")
    @Pattern(regexp = NAME_REGEX, message = NAME_MESSAGE)
    private String lastname;

    @NotBlank(message = "Username is required")
    @Size(min = USERNAME_MIN, max = USERNAME_MAX,
            message = "Username must be between " + USERNAME_MIN + " and " + USERNAME_MAX + " characters")
    @Pattern(regexp = USERNAME_REGEX, message = USERNAME_MESSAGE)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = PASSWORD_MIN, max = PASSWORD_MAX,
            message = "Password must be between " + PASSWORD_MIN + " and " + PASSWORD_MAX + " characters")
    @Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_MESSAGE)
    private String password;

    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
}
