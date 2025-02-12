package com.jaydenroeper.workouttracker.backend.workout.domain;

import java.time.LocalDate;

import static com.jaydenroeper.workouttracker.backend.workout.config.ValidationConstants.*;

public class Workout {

    private Long id;

    private UserProfile userProfile;

    private String name;
    private LocalDate date;

    public Workout(String name, LocalDate date) {
        validateWorkoutName(name);
        validateWorkoutDate(date);

        this.name = name;
        this.date = date;
    }

    private void validateWorkoutName(String name) {
        if (name == null || name.isEmpty() || name.length() > WORKOUT_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(WORKOUT_NAME_LENGTH_MESSAGE);
        }
        if (!name.matches(WORKOUT_NAME_REGEX)) {
            throw new IllegalArgumentException(WORKOUT_NAME_REGEX_MESSAGE);
        }
    }

    private void validateWorkoutDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Workout date cannot be null.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Workout date cannot be in the future.");
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Workout{")
                .append("name=").append(name)
                .append(", date=").append(date)
                .append("}");

        return sb.toString();
    }
}
