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
//        Exercise 1
        WorkoutExercise chestPressExercise = new WorkoutExercise(upperBodyWorkout, chestPress);
        chestPressExercise.addSet(new ExerciseSetBuilder().weight(27.5).reps(7).build());
        chestPressExercise.addSet(new ExerciseSetBuilder().weight(25).reps(6).build());
        chestPressExercise.addSet(new ExerciseSetBuilder().weight(20).reps(12).build());
        upperBodyWorkout.addExercise(chestPressExercise);
//        Exercise 2
        WorkoutExercise pullUpsExercise = new WorkoutExercise(upperBodyWorkout, pullUps);
        pullUpsExercise.addSet(new ExerciseSetBuilder().reps(12).build());
        pullUpsExercise.addSet(new ExerciseSetBuilder().reps(9).build());
        pullUpsExercise.addSet(new ExerciseSetBuilder().reps(5).build());
        upperBodyWorkout.addExercise(pullUpsExercise);
//        Exercise 3
        WorkoutExercise weightedDipsExercise = new WorkoutExercise(upperBodyWorkout, weightedDips);
        weightedDipsExercise.addSet(new ExerciseSetBuilder().weight(20).reps(7).build());
        weightedDipsExercise.addSet(new ExerciseSetBuilder().weight(15).reps(7).build());
        weightedDipsExercise.addSet(new ExerciseSetBuilder().weight(10).reps(7).build());
        upperBodyWorkout.addExercise(weightedDipsExercise);
//        Exercise 4
        WorkoutExercise plankingExercise = new WorkoutExercise(upperBodyWorkout, planking);
        plankingExercise.addSet(new ExerciseSetBuilder().time(60).build());
        plankingExercise.addSet(new ExerciseSetBuilder().time(60).build());
        plankingExercise.addSet(new ExerciseSetBuilder().time(45).build());

        System.out.println(upperBodyWorkout.getExercises());
    }
}
