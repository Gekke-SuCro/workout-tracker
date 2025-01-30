package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;

public interface AuthService {

    String verify(LoginRequestDto loginRequestDto);
}
