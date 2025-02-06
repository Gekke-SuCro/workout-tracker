package com.jaydenroeper.workouttracker.backend.utils;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

public class TestObjectUtils {

    public static Roles getUserRole() {
        return new Roles(0, "ROLE_USER");
    }

    public static Users createUser(String username, String password) {
        return Users.builder()
                .firstname("John")
                .lastname("Doe")
                .username(username)
                .password(new BCryptPasswordEncoder(12).encode(password))
                .roles(Collections.singleton(getUserRole()))
                .build();
    }

    public static Users createUser() {
        return createUser("user", "user");
    }

    public static RegisterRequestDto createRegisterRequestDto() {
        return new RegisterRequestDto(
                "John",
                "Doe",
                "user",
                "password",
                "password"
        );
    }
}
