package com.jaydenroeper.workouttracker.backend.workout.application.dto;

import java.time.LocalDate;
import java.util.List;

public record WorkoutResponseDto(
        Long id,
        String name,
        LocalDate date,
        List<WorkoutExerciseResponseDto> exercises
) {
}
