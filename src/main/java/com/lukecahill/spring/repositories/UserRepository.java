package com.lukecahill.spring.repositories;

import com.lukecahill.spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {
    @Transactional
    User findOneByUsername(String username);
}
