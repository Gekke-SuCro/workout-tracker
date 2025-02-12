package com.jaydenroeper.workouttracker.backend.workout.domain;

public class WorkoutExercise {

    private Long id;
    private Workout workout;
    private Exercise exercise;

    public WorkoutExercise(Workout workout, Exercise exercise) {
        this.workout = workout;
        this.exercise = exercise;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("WorkoutExercise{")
                .append("exercise=").append(exercise)
                .append("}");

        return sb.toString();
    }
}
