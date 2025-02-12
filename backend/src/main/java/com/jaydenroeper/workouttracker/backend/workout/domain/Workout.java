package com.jaydenroeper.workouttracker.backend.workout.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.jaydenroeper.workouttracker.backend.workout.config.ValidationConstants.*;

public class Workout {

    private Long id;

    private UserProfile userProfile;

    private String name;
    private LocalDate date;

    private List<WorkoutExercise> exercises;

    public Workout(String name, LocalDate date) {
        validateWorkoutName(name);
        validateWorkoutDate(date);

        this.name = name;
        this.date = date;
        this.exercises = new ArrayList<>();
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

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void addExercise(WorkoutExercise exercise) {
        if (exercises.contains(exercise)) {
            return;
        }
        exercises.add(exercise);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Workout{")
                .append("name=").append(name)
                .append(", date=").append(date)
                .append(", exercises=").append(exercises)
                .append("}");

        return sb.toString();
    }
}
