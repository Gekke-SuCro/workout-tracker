package com.jaydenroeper.workouttracker.backend.workout.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExerciseValueType type;

    protected Exercise() {
    }

    public Exercise(String name, ExerciseValueType type) {
        this.name = name;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ExerciseValueType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name=" + name +
                "}";
    }
}
