package com.jaydenroeper.workouttracker.backend.workout.data;

import com.jaydenroeper.workouttracker.backend.workout.domain.UserProfile;
import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    Optional<Workout> findByUserProfile(UserProfile userProfile);
}
