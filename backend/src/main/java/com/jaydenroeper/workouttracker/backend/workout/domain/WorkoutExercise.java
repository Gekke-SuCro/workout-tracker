package com.jaydenroeper.workouttracker.backend.workout.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workout_exercise")
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> sets;

    protected WorkoutExercise() {
    }

    public WorkoutExercise(Workout workout, Exercise exercise) {
        this.workout = workout;
        this.exercise = exercise;
        this.sets = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public Workout getWorkout() {
        return workout;
    }

    public List<ExerciseSet> getSets() {
        return sets;
    }

    public void addSet(ExerciseSet exerciseSet) {
        if (sets.contains(exerciseSet)) {
            return;
        }
        sets.add(exerciseSet);
    }

    @Override
    public String toString() {
        return "WorkoutExercise{" +
                "exercise=" + exercise +
                ", sets=" + sets +
                "}";
    }
}
