package com.jaydenroeper.workouttracker.backend.security.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.stream.Stream;

import static com.jaydenroeper.workouttracker.backend.security.config.ValidationConstants.*;
import static com.jaydenroeper.workouttracker.backend.security.utils.UsersTestFactory.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {

    private Users users;

    private static Stream<String> provideInvalidNames() {
        return Stream.of(
                null,
                "",
                "A".repeat(USERNAME_MIN_LENGTH - 1),
                "A".repeat(USERNAME_MAX_LENGTH + 1),
                "123username",
                "user.name",
                "_username",
                "user@name",
                "username!"
        );
    }

    public static Stream<String> provideInvalidPasswords() {
        return Stream.of(
                null,
                "",
                " ",
                " password",
                "pass word",
                " password ",
                "A".repeat(PASSWORD_MIN_LENGTH - 1),
                "A".repeat(PASSWORD_MAX_LENGTH + 1)
        );
    }

    @BeforeEach
    void setUp() {
        users = createUser(DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_ROLES);
    }

    @Test
    void Should_CreateUser_When_ValidArguments() {
        assertNotNull(users);
        assertEquals(DUMMY_USERNAME, users.getUsername());
        assertEquals(DUMMY_ROLES, users.getRoles());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNames")
    void Should_ThrowException_When_NameIsInvalid(String username) {
        assertThrows(IllegalArgumentException.class, () -> createUser(username, DUMMY_PASSWORD, DUMMY_ROLES));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPasswords")
    void Should_ThrowException_When_PasswordIsInvalid(String password) {
        assertThrows(IllegalArgumentException.class, () -> createUser(DUMMY_USERNAME, password, DUMMY_ROLES));
    }

    @Test
    void Should_ThrowException_When_RolesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> createUser(DUMMY_USERNAME, DUMMY_PASSWORD, null));
    }

    @Test
    void Should_ThrowException_When_RolesIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> createUser(DUMMY_USERNAME, DUMMY_PASSWORD, new HashSet<>()));
    }
}
