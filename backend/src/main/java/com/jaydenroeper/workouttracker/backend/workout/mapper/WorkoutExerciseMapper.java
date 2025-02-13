package com.jaydenroeper.workouttracker.backend.workout.mapper;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.ExerciseSetResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.application.dto.WorkoutExerciseResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.application.exception.ExerciseNotFoundException;
import com.jaydenroeper.workouttracker.backend.workout.data.ExerciseRepository;
import com.jaydenroeper.workouttracker.backend.workout.domain.Exercise;
import com.jaydenroeper.workouttracker.backend.workout.domain.ExerciseSet;
import com.jaydenroeper.workouttracker.backend.workout.domain.WorkoutExercise;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.ExerciseSetRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutExerciseRequestDto;
import org.hibernate.jdbc.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkoutExerciseMapper {

    public static WorkoutExerciseResponseDto toDto(WorkoutExercise workoutExercise) {
        List<ExerciseSetResponseDto> sets = new ArrayList<>();
        for (ExerciseSet es : workoutExercise.getSets()) {
            sets.add(ExerciseSetMapper.toDto(es));
        }

        return new WorkoutExerciseResponseDto(
                workoutExercise.getId(),
                workoutExercise.getExercise().getName(),
                sets
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
