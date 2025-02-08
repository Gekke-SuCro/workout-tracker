package com.jaydenroeper.workouttracker.backend.security.validation;

public class ValidationConstants {

    // Name Rules
    public static final int NAME_MIN = 2;
    public static final int NAME_MAX = 50;
    public static final String NAME_REGEX = "^[A-Za-z]+$";
    public static final String NAME_MESSAGE = "First name and last name must contain only alphabets without spaces.";

    // Username Rules
    public static final int USERNAME_MIN = 3;
    public static final int USERNAME_MAX = 30;
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9]*$";
    public static final String USERNAME_MESSAGE = "Username must start with a letter and contain no spaces.";

    // Password Rules
    public static final int PASSWORD_MIN = 8;
    public static final int PASSWORD_MAX = 128;
    public static final String PASSWORD_REGEX = "^(?!.*\\s).*$";
    public static final String PASSWORD_MESSAGE = "Password cannot contain whitespaces.";

    private ValidationConstants() {

    }
}
