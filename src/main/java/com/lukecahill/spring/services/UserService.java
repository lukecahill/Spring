package com.lukecahill.spring.services;

import com.lukecahill.spring.models.User;
import com.lukecahill.spring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public User createNewUser(String name, String username, String email, String password, boolean enabled) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        password = bCryptPasswordEncoder.encode(password);
        User user = new User(name, username, email, password, enabled);
        entityManager.persist(user);

        return user;
    }
}
