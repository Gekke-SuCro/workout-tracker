package com.jaydenroeper.workouttracker.backend.security.mapper;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UsersMapper {

    private final BCryptPasswordEncoder encoder;

    public UsersMapper(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Users toUser(RegisterRequestDto registerRequestDto, Set<Roles> roles) {
        return new Users(
                registerRequestDto.firstname(),
                registerRequestDto.lastname(),
                registerRequestDto.username(),
                encoder.encode(registerRequestDto.password()),
                roles
        );
    }
}
