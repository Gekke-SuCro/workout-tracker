package com.jaydenroeper.workouttracker.backend;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.workout.domain.UserProfile;
import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;

import java.time.LocalDate;
import java.util.Set;

public class WorkoutDomainTest {

    public static void main(String[] args) {
        Users user = new Users("user", "password", Set.of(new Roles("ROLE_USER")));
        UserProfile userProfile = new UserProfile(user, 62.7, 178);

        Workout workout = new Workout("Incline Chest Press", LocalDate.now());

        userProfile.addWorkout(workout);
        System.out.println(userProfile);
    }
}
