package com.lukecahill.spring.repositories;

import com.lukecahill.spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findOneByUsername(String username);
}
