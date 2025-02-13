package com.jaydenroeper.workouttracker.backend.workout.application.dto;

import com.jaydenroeper.workouttracker.backend.workout.domain.ExerciseSet;
import com.jaydenroeper.workouttracker.backend.workout.domain.WorkoutExercise;

import java.util.ArrayList;
import java.util.List;

public record WorkoutExerciseResponseDto(
        String name,
        List<ExerciseSetResponseDto> sets
) {

    public static WorkoutExerciseResponseDto fromWorkoutExercise(WorkoutExercise workoutExercise) {
        List<ExerciseSetResponseDto> sets = new ArrayList<>();
        for (ExerciseSet es : workoutExercise.getSets()) {
            sets.add(ExerciseSetResponseDto.fromExerciseSet(es));
        }

        return new WorkoutExerciseResponseDto(
                workoutExercise.getExercise().getName(),
                sets
        );
    }

}
