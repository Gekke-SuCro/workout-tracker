package com.jaydenroeper.workouttracker.backend.security.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.stream.Stream;

import static com.jaydenroeper.workouttracker.backend.security.config.AuthValidationConstants.*;
import static com.jaydenroeper.workouttracker.backend.security.utils.UsersTestFactory.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsersTest {

    private Users users;

    private static Stream<Arguments> provideInvalidNames() {
        return Stream.of(
                Arguments.of(null, DUMMY_LASTNAME, DUMMY_USERNAME, "First name cannot be null"),
                Arguments.of("", DUMMY_LASTNAME, DUMMY_USERNAME, "First name"),
                Arguments.of("A".repeat(NAME_MIN) + " ", DUMMY_LASTNAME, DUMMY_USERNAME, "First name"),
                Arguments.of("A".repeat(NAME_MIN - 1), DUMMY_LASTNAME, DUMMY_USERNAME, "First name"),
                Arguments.of("A".repeat(NAME_MAX + 1), DUMMY_LASTNAME, DUMMY_USERNAME, "First name"),

                Arguments.of(DUMMY_FIRSTNAME, null, DUMMY_USERNAME, "Last name"),
                Arguments.of(DUMMY_FIRSTNAME, "", DUMMY_USERNAME, "Last name"),
                Arguments.of(DUMMY_FIRSTNAME, "A".repeat(NAME_MIN) + " ", DUMMY_USERNAME, "Last name"),
                Arguments.of(DUMMY_FIRSTNAME, "A".repeat(NAME_MIN - 1), DUMMY_USERNAME, "Last name"),
                Arguments.of(DUMMY_FIRSTNAME, "A".repeat(NAME_MAX + 1), DUMMY_USERNAME, "Last name"),

                Arguments.of(DUMMY_FIRSTNAME, DUMMY_LASTNAME, null, "Username"),
                Arguments.of(DUMMY_FIRSTNAME, DUMMY_LASTNAME, "", "Username"),
                Arguments.of(DUMMY_FIRSTNAME, DUMMY_LASTNAME, "A".repeat(USERNAME_MIN) + " ", "Username"),
                Arguments.of(DUMMY_FIRSTNAME, DUMMY_LASTNAME, "A".repeat(USERNAME_MIN - 1), "Username"),
                Arguments.of(DUMMY_FIRSTNAME, DUMMY_LASTNAME, "A".repeat(USERNAME_MAX + 1), "Username")
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
                "A".repeat(PASSWORD_MIN - 1),
                "A".repeat(PASSWORD_MAX + 1)
        );
    }

    @BeforeEach
    void setUp() {
        users = createUser();
    }

    @Test
    void Should_CreateUser_When_ValidArguments() {
        assertNotNull(users);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNames")
    void Should_ThrowException_When_NameIsInvalid(String firstName, String lastName, String username, String fieldName) {
        assertThrows(IllegalArgumentException.class, () -> createUser(firstName, lastName, username),
                fieldName + " validation failed");
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPasswords")
    void Should_ThrowException_When_PasswordIsInvalid(String password) {
        assertThrows(IllegalArgumentException.class, () -> createUser(password),
                "password validation failed");
    }

    @Test
    void Should_ThrowException_When_RolesIsNull() {
        assertThrows(IllegalArgumentException.class, () -> createUserWithCustomRoles(null));
    }

    @Test
    void Should_ThrowException_When_RolesIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> createUserWithCustomRoles(new HashSet<>()));
    }
}
