package com.jaydenroeper.workouttracker.backend.workout.application.dto;

import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;
import com.jaydenroeper.workouttracker.backend.workout.domain.WorkoutExercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record WorkoutResponseDto(
        String username,
        String name,
        LocalDate date,
        List<WorkoutExerciseResponseDto> exercises
) {
}
