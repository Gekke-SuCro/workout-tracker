package com.jaydenroeper.workouttracker.backend.workout.application.dto;

import com.jaydenroeper.workouttracker.backend.workout.domain.ExerciseValueType;

public record ExerciseResponseDto(
        Long id,
        String name,
        ExerciseValueType type
) {
}