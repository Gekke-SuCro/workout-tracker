package com.jaydenroeper.workouttracker.backend;

import com.jaydenroeper.workouttracker.backend.security.domain.Roles;
import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import com.jaydenroeper.workouttracker.backend.workout.domain.*;

import java.time.LocalDate;
import java.util.Set;

public class WorkoutDomainTest {

    public static void main(String[] args) {
        Users user = new Users("user", "password", Set.of(new Roles("ROLE_USER")));
        UserProfile userProfile = new UserProfile(user, 62.7, 178);

        Exercise chestPress = new Exercise("Chest Press", ExerciseValueType.WEIGHT);
        Exercise pullUps = new Exercise("Pull Ups", ExerciseValueType.BODY_WEIGHT);
        Exercise weightedDips = new Exercise("Weighted Dips", ExerciseValueType.WEIGHT);
        Exercise planking = new Exercise("Planking", ExerciseValueType.SECONDS);

//        Create workout
        Workout upperBodyWorkout = new Workout("Upper Body Workout", LocalDate.now());
        userProfile.addWorkout(upperBodyWorkout);

        WorkoutExercise chestPressExercise = new WorkoutExercise(upperBodyWorkout, chestPress);
        chestPressExercise.addSet(new ExerciseSetBuilder().weight(27.5).reps(7).build());
        chestPressExercise.addSet(new ExerciseSetBuilder().weight(25).reps(6).build());
        chestPressExercise.addSet(new ExerciseSetBuilder().weight(20).reps(12).build());
        upperBodyWorkout.addExercise(chestPressExercise);

        System.out.println(upperBodyWorkout.getExercises());
    }
}
