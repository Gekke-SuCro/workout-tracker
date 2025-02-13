package com.jaydenroeper.workouttracker.backend.workout.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise_set")
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_exercise_id", nullable = false)
    private WorkoutExercise workoutExercise;

    private int reps;
    private double weight;
    private double time;

    protected ExerciseSet() {
    }

    public ExerciseSet(int reps, double weight, double time) {
        this.reps = reps;
        this.weight = weight;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public double getTime() {
        return time;
    }

    public WorkoutExercise getWorkoutExercise() {
        return workoutExercise;
    }

    public void setWorkoutExercise(WorkoutExercise workoutExercise) {
        this.workoutExercise = workoutExercise;
    }

    @Override
    public String toString() {
        return "ExerciseSet{" +
                "reps=" + reps +
                ", weight=" + weight +
                ", time=" + time +
                "}";
    }
}
