package com.jaydenroeper.workouttracker.backend.workout.config;

public class ValidationConstants {

    public static final int WORKOUT_NAME_MIN_LENGTH = 1;
    public static final int WORKOUT_NAME_MAX_LENGTH = 50;
    public static final String WORKOUT_NAME_LENGTH_MESSAGE = "Workout name must be between " + WORKOUT_NAME_MIN_LENGTH + " and " + WORKOUT_NAME_MAX_LENGTH + " charachters.";
    public static final String WORKOUT_NAME_REGEX = "^(?!\\s)[\\s\\S]*(?<!\\s)$";
    public static final String WORKOUT_NAME_REGEX_MESSAGE = "Workout name cannot start or end with whitespace character.";
}
