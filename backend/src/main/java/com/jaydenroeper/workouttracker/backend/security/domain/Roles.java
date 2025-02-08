package com.jaydenroeper.workouttracker.backend.security.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    protected Roles() {}

    public Roles(String name) {
        if (name == null || !name.startsWith("ROLE_")) {
            throw new IllegalArgumentException("Role name must start with 'ROLE_'-prefix.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
