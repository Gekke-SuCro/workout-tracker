package com.jaydenroeper.workouttracker.backend.workout.domain;

import com.jaydenroeper.workouttracker.backend.security.domain.Users;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Users user;

    private double weight;
    private double length;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Workout> workouts;

    protected UserProfile() {
    }

    public UserProfile(Users user, double weight, double length) {
        this.user = user;
        this.weight = weight;
        this.length = length;
        this.workouts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Users getUser() {
        return user;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public void addWorkout(Workout workout) {
        if (workouts.contains(workout)) {
            return;
        }

        workouts.add(workout);
        workout.setUserProfile(this);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "weight=" + weight +
                ", length=" + length +
                ", workouts=" + workouts +
                "}";
    }
}
