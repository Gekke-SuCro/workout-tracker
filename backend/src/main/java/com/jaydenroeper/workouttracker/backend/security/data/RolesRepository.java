package com.jaydenroeper.workouttracker.backend.security.data;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Roles;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String roleName);
}