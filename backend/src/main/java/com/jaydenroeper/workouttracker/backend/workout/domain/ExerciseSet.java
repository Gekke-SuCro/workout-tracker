package com.jaydenroeper.workouttracker.backend.workout.domain;

public class ExerciseSet {

    private Long id;

    private int reps;
    private double weigth;
    private double time;

    public ExerciseSet(int reps, double weigth, double time) {
        this.reps = reps;
        this.weigth = weigth;
        this.time = time;
    }

    public int getReps() {
        return reps;
    }

    public double getWeigth() {
        return weigth;
    }

    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ExerciseSet{")
                .append("reps=").append(reps)
                .append(", weight=").append(weigth)
                .append(", time=").append(time)
                .append("}");

        return sb.toString();
    }
}
