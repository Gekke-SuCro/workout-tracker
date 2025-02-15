package com.jaydenroeper.workouttracker.backend.workout.mapper;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.ExerciseResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.domain.Exercise;

public class ExerciseMapper {

    public static ExerciseResponseDto toDto(Exercise exercise) {
        return new ExerciseResponseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getType()
        );
    }
}
