package com.jaydenroeper.workouttracker.backend.utils;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.security.presentation.dto.RegisterRequestDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

public class TestObjectUtils {

    private static final String dummyFirstname = "John";
    private static final String dummyLastname = "Doe";
    private static final String dummyUsername = "user";
    private static final String dummyUserPassword = "password";

    public static Roles getUserRole() {
        return new Roles(0, "ROLE_USER");
    }

    public static Users createUser(String username, String password) {
        return Users.builder()
                .firstname(dummyFirstname)
                .lastname(dummyLastname)
                .username(username)
                .password(new BCryptPasswordEncoder(12).encode(password))
                .roles(Collections.singleton(getUserRole()))
                .build();
    }

    public static Users createUser() {
        return createUser(dummyUsername, dummyUserPassword);
    }

    public static RegisterRequestDto createRegisterRequestDto() {
        return new RegisterRequestDto(
                dummyFirstname,
                dummyLastname,
                dummyUsername,
                dummyUserPassword,
                dummyUserPassword
        );
    }
}
