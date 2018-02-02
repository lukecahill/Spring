package com.lukecahill.spring.repositories;

import com.lukecahill.spring.models.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
}
