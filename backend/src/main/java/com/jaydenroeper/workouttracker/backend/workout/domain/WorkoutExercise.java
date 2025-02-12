package com.jaydenroeper.workouttracker.backend.workout.domain;

import java.util.ArrayList;
import java.util.List;

public class WorkoutExercise {

    private Long id;
    private Workout workout;
    private Exercise exercise;
    private List<ExerciseSet> sets;

    public WorkoutExercise(Workout workout, Exercise exercise) {
        this.workout = workout;
        this.exercise = exercise;
        this.sets = new ArrayList<>();
    }

    public void addSet(ExerciseSet exerciseSet) {
        if (sets.contains(exerciseSet)) {
            return;
        }
        sets.add(exerciseSet);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("WorkoutExercise{")
                .append("exercise=").append(exercise)
                .append(", sets=").append(sets)
                .append("}");

        return sb.toString();
    }
}
