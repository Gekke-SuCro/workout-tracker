package com.jaydenroeper.workouttracker.backend.workout.domain;

public class ExerciseSetBuilder {
    private int reps;
    private double weigth;
    private double time;

    public ExerciseSetBuilder reps(int reps) {
        this.reps = reps;
        return this;
    }

    public ExerciseSetBuilder weight(double weight) {
        this.weigth = weight;
        return this;
    }

    public ExerciseSetBuilder time(double time) {
        this.time = time;
        return this;
    }

    public ExerciseSet build() {
        return new ExerciseSet(reps, weigth, time);
    }
}
