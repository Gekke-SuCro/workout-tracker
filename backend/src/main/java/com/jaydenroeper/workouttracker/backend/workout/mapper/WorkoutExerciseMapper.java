package com.jaydenroeper.workouttracker.backend.workout.mapper;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.WorkoutExerciseResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.domain.Exercise;
import com.jaydenroeper.workouttracker.backend.workout.domain.WorkoutExercise;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.ExerciseSetRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutExerciseRequestDto;

public class WorkoutExerciseMapper {

    public static WorkoutExerciseResponseDto toDto(WorkoutExercise workoutExercise) {
        return new WorkoutExerciseResponseDto(
                workoutExercise.getId(),
                workoutExercise.getExercise().getName(),
                workoutExercise.getSets().stream().map(ExerciseSetMapper::toDto).toList()
        );
    }

    public static WorkoutExercise toWorkoutExercise(WorkoutExerciseRequestDto dto, Exercise exercise) {
        WorkoutExercise workoutExercise = new WorkoutExercise(exercise);
        for (ExerciseSetRequestDto es : dto.sets()) {
            workoutExercise.addSet(ExerciseSetMapper.toExerciseSet(es));
        }
        return workoutExercise;
    }
}
