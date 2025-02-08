package com.jaydenroeper.workouttracker.backend.security.domain;

import org.junit.jupiter.api.Test;

import static com.jaydenroeper.workouttracker.backend.security.utils.RolesTestFactory.createRole;
import static org.junit.jupiter.api.Assertions.*;


public class RolesTest {

    @Test
    void Should_CreateRoles_When_ValidRoleName() {
        String userRoleName = "ROLE_USER";
        Roles roles = createRole(userRoleName);

        assertNotNull(roles);
        assertEquals(userRoleName, roles.getName());
    }

    @Test
    void Should_ThrowException_When_RoleNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> createRole(null));
    }

    @Test
    void Should_ThrowException_When_RoleNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> createRole(""));
    }

    @Test
    void Should_ThrowException_When_InValidRoleName() {
        assertThrows(IllegalArgumentException.class, () -> createRole("USER"));
    }
}
