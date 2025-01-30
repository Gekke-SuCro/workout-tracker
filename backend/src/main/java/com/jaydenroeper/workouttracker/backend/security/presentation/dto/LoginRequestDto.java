package com.jaydenroeper.workouttracker.backend.security.presentation.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;
    private String password;
}
