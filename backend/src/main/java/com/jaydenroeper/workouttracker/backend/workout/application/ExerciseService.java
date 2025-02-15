package com.jaydenroeper.workouttracker.backend.workout.application;

import com.jaydenroeper.workouttracker.backend.workout.application.dto.ExerciseResponseDto;
import com.jaydenroeper.workouttracker.backend.workout.data.ExerciseRepository;
import com.jaydenroeper.workouttracker.backend.workout.mapper.ExerciseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<ExerciseResponseDto> findAllExercises() {
        return exerciseRepository.findAll().stream()
                .map(ExerciseMapper::toDto)
                .toList();
    }
}
