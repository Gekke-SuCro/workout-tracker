package com.jaydenroeper.workouttracker.backend.workout.mapper;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.WorkoutExerciseResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.application.dto.WorkoutResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.data.ExerciseRepository;
import com.jaydenroeper.workouttracker.backend.workout.domain.Exercise;
import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;
import com.jaydenroeper.workouttracker.backend.workout.domain.WorkoutExercise;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.ExerciseSetRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutExerciseRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class WorkoutMapper {

    public static WorkoutResponseDto toDto(Workout workout) {
        List<WorkoutExerciseResponseDto> exercises = new ArrayList<>();
        for (WorkoutExercise ex : workout.getExercises()) {
            exercises.add(WorkoutExerciseMapper.toDto(ex));
        }

        return new WorkoutResponseDto(
                workout.getUserProfile().getUsername(),
                workout.getName(),
                workout.getDate(),
                exercises
        );
    }

    public static Workout toWorkout(WorkoutRequestDto workoutRequestDto, ExerciseRepository exerciseRepository) {
        Workout workout = new Workout(workoutRequestDto.name(),  workoutRequestDto.date());

        for (WorkoutExerciseRequestDto ex : workoutRequestDto.exercises()) {
            workout.addExercise(WorkoutExerciseMapper.toWorkoutExercise(ex, exerciseRepository));
        }

        return workout;
    }
}
