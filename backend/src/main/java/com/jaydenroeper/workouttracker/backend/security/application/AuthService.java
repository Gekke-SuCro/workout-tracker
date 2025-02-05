package com.jaydenroeper.workouttracker.backend.security.application;

import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.LoginRequestDto;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;

public interface AuthService {

    String verify(LoginRequestDto loginRequestDto);

    Users register(RegisterRequestDto registerRequestDto);
}
