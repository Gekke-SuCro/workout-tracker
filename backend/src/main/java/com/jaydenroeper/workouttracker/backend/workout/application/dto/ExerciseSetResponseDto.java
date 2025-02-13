package com.jaydenroeper.workouttracker.backend.workout.application.dto;

import com.jaydenroeper.workouttracker.backend.workout.domain.ExerciseSet;

public record ExerciseSetResponseDto(
        int reps,
        double weigth,
        double time
) {

    public static ExerciseSetResponseDto fromExerciseSet(ExerciseSet exerciseSet) {
        return new ExerciseSetResponseDto(
                exerciseSet.getReps(),
                exerciseSet.getWeigth(),
                exerciseSet.getTime()
        );
    }
}
