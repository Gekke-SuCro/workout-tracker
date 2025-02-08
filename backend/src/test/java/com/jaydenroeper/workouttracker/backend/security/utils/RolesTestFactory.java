package com.jaydenroeper.workouttracker.backend.security.utils;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;

import static com.jaydenroeper.workouttracker.backend.security.config.SecurityConstants.DEFAULT_ROLE;

public class RolesTestFactory {

    public static Roles createRole(String roleName) {
        return new Roles(roleName);
    }

    public static Roles createRole() {
        return createRole(DEFAULT_ROLE);
    }
}
