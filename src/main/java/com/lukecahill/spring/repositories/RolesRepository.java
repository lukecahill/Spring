package com.lukecahill.spring.repositories;

import com.lukecahill.spring.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
