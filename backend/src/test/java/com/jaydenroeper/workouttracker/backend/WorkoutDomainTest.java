package com.jaydenroeper.workouttracker.backend;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.workout.domain.Exercise;
import com.jaydenroeper.workouttracker.backend.workout.domain.UserProfile;
import com.jaydenroeper.workouttracker.backend.workout.domain.Workout;
import com.jaydenroeper.workouttracker.backend.workout.domain.WorkoutExercise;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class WorkoutDomainTest {

    public static void main(String[] args) {
        Users user = new Users("user", "password", Set.of(new Roles("ROLE_USER")));
        UserProfile userProfile = new UserProfile(user, 62.7, 178);

        Exercise exercise = new Exercise("Incline Chest Press");
        Exercise exercise2 = new Exercise("Weighted Dips");
        Exercise exercise3 = new Exercise("Decline Chest Press");


        Workout workout = new Workout("Chest Workout", LocalDate.now());
        userProfile.addWorkout(workout);

        List<Exercise> wEx = List.of(exercise, exercise2, exercise3);
        for (Exercise ex : wEx) {
            workout.addExercise(new WorkoutExercise(workout, ex));
        }

        System.out.println(workout.getExercises());
    }
}
