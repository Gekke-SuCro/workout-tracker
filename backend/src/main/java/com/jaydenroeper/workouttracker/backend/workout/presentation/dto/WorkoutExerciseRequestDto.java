package com.jaydenroeper.workouttracker.backend.workout.presentation.dto;

import java.util.List;

public record WorkoutExerciseRequestDto(
        Long id,
        String name,
        List<ExerciseSetRequestDto> sets
) {
}
