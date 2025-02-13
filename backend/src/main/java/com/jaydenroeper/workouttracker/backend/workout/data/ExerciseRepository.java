package com.jaydenroeper.workouttracker.backend.workout.data;

import com.jaydenroeper.workouttracker.backend.workout.domain.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    Optional<Exercise> findByName(String name);

    List<Exercise> findAllByNameIn(Collection<String> name);
}
