package com.jaydenroeper.workouttracker.backend.workout.application.dto;

public record ExerciseSetResponseDto(
       Long id,
        int reps,
        double weight,
        double time
) {
}
