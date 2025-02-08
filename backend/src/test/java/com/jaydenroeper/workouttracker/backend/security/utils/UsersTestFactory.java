package com.jaydenroeper.workouttracker.backend.security.utils;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;

import java.util.Set;

import static com.jaydenroeper.workouttracker.backend.security.utils.RolesTestFactory.createRole;

public class UsersTestFactory {

    public static final String DUMMY_USERNAME = "user";
    public static final String DUMMY_PASSWORD = "password";
    public static final Set<Roles> DUMMY_ROLES = Set.of(createRole());

    public static Users createUser(String username, String password, Set<Roles> roles) {
        return new Users(username, password, roles);
    }
}
