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

    public static WorkoutResponseDto fromWorkout(Workout workout) {
        List<WorkoutExerciseResponseDto> exercises = new ArrayList<>();
        for (WorkoutExercise ex : workout.getExercises()) {
            exercises.add(WorkoutExerciseResponseDto.fromWorkoutExercise(ex));
        }

        return new WorkoutResponseDto(
                workout.getUserProfile().getUsername(),
                workout.getName(),
                workout.getDate(),
                exercises
        );
    }
}
