package com.jaydenroeper.workouttracker.backend.security.utils;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;

import java.util.Set;

import static com.jaydenroeper.workouttracker.backend.security.utils.RolesTestFactory.createRole;

public class UsersTestFactory {

    public static final String DUMMY_FIRSTNAME = "John";
    public static final String DUMMY_LASTNAME = "John";
    public static final String DUMMY_USERNAME = "user";
    public static final String DUMMY_PASSWORD = "password";
    public static final Set<Roles> DUMMY_ROLES = Set.of(createRole());

    public static Users createUser(String firstName, String lastName, String username, String password, Set<Roles> roles) {
        return new Users(firstName, lastName, username, password, roles);
    }

    public static Users createUser() {
        return new Users(DUMMY_FIRSTNAME, DUMMY_LASTNAME, DUMMY_USERNAME, DUMMY_PASSWORD, DUMMY_ROLES);
    }

    public static Users createUserWithCustomRoles(Set<Roles> roles) {
        return new Users(DUMMY_FIRSTNAME, DUMMY_LASTNAME, DUMMY_USERNAME, DUMMY_PASSWORD, roles);
    }

    public static Users createUser(String password) {
        return createUser(DUMMY_FIRSTNAME, DUMMY_LASTNAME, DUMMY_USERNAME, password, DUMMY_ROLES);
    }

    public static Users createUser(String dummyFirstname, String dummyLastname, String username) {
        return createUser(dummyFirstname, dummyLastname, username, DUMMY_PASSWORD, DUMMY_ROLES);
    }
}
