package com.jaydenroeper.workouttracker.backend.workout.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.jaydenroeper.workouttracker.backend.workout.config.ValidationConstants.*;

@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfile userProfile;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutExercise> exercises;

    protected Workout() {
    }

    public Workout(String name, LocalDate date) {
        validateWorkoutName(name);
        validateWorkoutDate(date);

        this.name = name;
        this.date = date;
        this.exercises = new ArrayList<>();
    }

    private void validateWorkoutName(String name) {
        if (name == null || name.isEmpty() || name.length() > WORKOUT_NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(WORKOUT_NAME_LENGTH_MESSAGE);
        }
        if (!name.matches(WORKOUT_NAME_REGEX)) {
            throw new IllegalArgumentException(WORKOUT_NAME_REGEX_MESSAGE);
        }
    }

    private void validateWorkoutDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Workout date cannot be null.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Workout date cannot be in the future.");
        }
    }

    public Long getId() {
        return id;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }


    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public void addExercise(WorkoutExercise exercise) {
        if (exercises.contains(exercise)) {
            return;
        }
        exercises.add(exercise);
        exercise.setWorkout(this);
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name=" + name +
                ", date=" + date +
                ", exercises=" + exercises +
                "}";
    }
}
