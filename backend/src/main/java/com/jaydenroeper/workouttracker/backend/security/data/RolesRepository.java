package com.jaydenroeper.workouttracker.backend.security.data;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jaydenroeper.workouttracker.backend.security.domain.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

}