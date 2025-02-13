package com.jaydenroeper.workouttracker.backend.workout.presentation.dto;

import java.time.LocalDate;
import java.util.List;

public record WorkoutRequestDto(
        String username,
        String name,
        LocalDate date,
        List<WorkoutExerciseRequestDto> exercises
) {
}
