package com.jaydenroeper.workouttracker.backend.workout.domain;


import java.time.LocalDate;

public class Validator {

    private static final String nameRegex = "^\\p{L}+(?: \\p{L}+)*$";

    public static void validateWorkout(String name, LocalDate date) {
//        Validate Workout Name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Workout name cannot be null or empty.");
        }
        if (name.length() > 50) {
            throw new IllegalArgumentException("Workout name cannot exceed 50 characters.");
        }
        if (!name.matches(nameRegex)) {
            throw new IllegalArgumentException("Workout name can only contain letters and no leading/trailing spaces allowed.");
        }
//        Validate Workout Date
        if (date == null) {
            throw new IllegalArgumentException("Workout date cannot be null.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Workout date cannot be in the future.");
        }
    }
}
