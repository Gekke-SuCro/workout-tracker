package com.jaydenroeper.workouttracker.backend.workout.presentation.dto;

import java.time.LocalDate;

public record WorkoutRequestDto(
        String username,
        String name,
        LocalDate date
) {
}
