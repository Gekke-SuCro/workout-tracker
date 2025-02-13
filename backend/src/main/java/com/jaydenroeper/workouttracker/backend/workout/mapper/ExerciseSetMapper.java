package com.jaydenroeper.workouttracker.backend.workout.mapper;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.ExerciseSetResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.domain.ExerciseSet;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.ExerciseSetRequestDto;

public class ExerciseSetMapper {

    public static ExerciseSet toExerciseSet(ExerciseSetRequestDto dto) {
        return new ExerciseSet(dto.reps(), dto.weight(), dto.time());
    }

    public static ExerciseSetResponseDto toDto(ExerciseSet exerciseSet) {
        return new ExerciseSetResponseDto(
                exerciseSet.getId(),
                exerciseSet.getReps(),
                exerciseSet.getWeight(),
                exerciseSet.getTime()
        );
    }
}
