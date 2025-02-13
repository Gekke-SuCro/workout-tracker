package com.jaydenroeper.workouttracker.backend.workout.application.dto;

import java.util.List;

public record WorkoutExerciseResponseDto(
        Long id,
        String name,
        List<ExerciseSetResponseDto> sets
) {
}
