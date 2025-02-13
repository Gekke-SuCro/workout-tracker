package com.jaydenroeper.workouttracker.backend.workout.application;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.WorkoutResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.application.exception.ExerciseNotFoundException;
import com.jaydenroeper.workouttracker.backend.workout.data.ExerciseRepository;
import com.jaydenroeper.workouttracker.backend.workout.data.UserProfileRepository;
import com.jaydenroeper.workouttracker.backend.workout.data.WorkoutRepository;
import com.jaydenroeper.workouttracker.backend.workout.domain.*;
import com.jaydenroeper.workouttracker.backend.workout.mapper.WorkoutMapper;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.ExerciseSetRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutExerciseRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutRequestDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findByUsername(username);
        if (optionalUserProfile.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        UserProfile userProfile = optionalUserProfile.get();
        Workout workout = WorkoutMapper.toWorkout(workoutRequestDto, exerciseRepository);
        userProfile.addWorkout(workout);
        workoutRepository.save(workout);
    }

    public List<WorkoutResponseDto> findAllWorkouts() {
        List<WorkoutResponseDto> workoutResponseDtos = new ArrayList<>();
        for (Workout workout : workoutRepository.findAll()) {
            workoutResponseDtos.add(WorkoutMapper.toDto(workout));
        }

        return workoutResponseDtos;
    }
}
