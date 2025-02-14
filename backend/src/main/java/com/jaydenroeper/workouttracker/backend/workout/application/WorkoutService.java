package com.jaydenroeper.workouttracker.backend.workout.application;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.WorkoutResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.application.exception.ExerciseNotFoundException;
import com.jaydenroeper.workouttracker.backend.workout.data.ExerciseRepository;
import com.jaydenroeper.workouttracker.backend.workout.data.UserProfileRepository;
import com.jaydenroeper.workouttracker.backend.workout.data.WorkoutRepository;
import com.jaydenroeper.workouttracker.backend.workout.domain.*;
import com.jaydenroeper.workouttracker.backend.workout.mapper.WorkoutMapper;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutExerciseRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutRequestDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserProfileRepository userProfileRepository;

    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, UserProfileRepository userProfileRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public void createWorkout(String username, WorkoutRequestDto workoutRequestDto) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//        Check if requested exercises exist in the database.
        Set<String> exerciseNames = workoutRequestDto.exercises().stream()
                .map(WorkoutExerciseRequestDto::name)
                .collect(Collectors.toSet());
        List<Exercise> exercises = exerciseRepository.findAllByNameIn(exerciseNames);
        if (exercises.size() != exerciseNames.size()) {
            throw new ExerciseNotFoundException("One or more exercises not found.");
        }

        Workout workout = WorkoutMapper.toWorkout(workoutRequestDto, exercises);
        userProfile.addWorkout(workout);
        workoutRepository.save(workout);
    }

    public List<WorkoutResponseDto> findAllWorkoutsByUsername(String username) {
        return workoutRepository.findAll().stream()
                .filter(workout -> workout.getUserProfile().getUsername().equals(username))
                .map(WorkoutMapper::toDto)
                .toList();
    }
}
