package com.jaydenroeper.workouttracker.backend.workout.application;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.WorkoutResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.data.UserProfileRepository;
import com.jaydenroeper.workouttracker.backend.workout.data.WorkoutRepository;
import com.jaydenroeper.workouttracker.backend.workout.presentation.dto.WorkoutRequestDto;
import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserProfileRepository userProfileRepository;

    public WorkoutService(WorkoutRepository workoutRepository, UserProfileRepository userProfileRepository) {
        this.workoutRepository = workoutRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public List<WorkoutResponseDto> findAllWorkouts() {
        List<WorkoutResponseDto> workoutResponseDtos = new ArrayList<>();
        for (Workout workout : workoutRepository.findAll()) {
            workoutResponseDtos.add(WorkoutResponseDto.fromWorkout(workout));
        }

        return workoutResponseDtos;
    }

    public Workout createWorkout(WorkoutRequestDto workoutRequestDto) {
        return null;
    }
}
