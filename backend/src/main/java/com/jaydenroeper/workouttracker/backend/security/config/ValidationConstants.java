package com.jaydenroeper.workouttracker.backend.security.config;

public class ValidationConstants {

    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 30;
    public static final String USERNAME_LENGTH_MESSAGE = "Username must be between " + USERNAME_MIN_LENGTH + " and " + USERNAME_MAX_LENGTH + " charachters.";
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9]*$";
    public static final String USERNAME_REGEX_MESSAGE = "Username must start with a letter and contain only letters and/ or numbers.";

    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 128;
    public static final String PASSWORD_LENGTH_MESSAGE = "Password must be between " + PASSWORD_MIN_LENGTH + " and " + PASSWORD_MAX_LENGTH + " charachters.";
    public static final String PASSWORD_REGEX = "^\\S+$";
    public static final String PASSWORD_REGEX_MESSAGE = "Password cannot contain whitespaces.";
}
