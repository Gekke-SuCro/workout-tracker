package com.jaydenroeper.workouttracker.backend.workout.presentation.dto;

public record ExerciseSetRequestDto (
    int reps,
    double weight,
    double time
) {
}
