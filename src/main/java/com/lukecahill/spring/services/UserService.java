package com.lukecahill.spring.services;

import com.lukecahill.spring.models.User;
import com.lukecahill.spring.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private UserRepository _userRepository;
    private EntityManager entityManager;

    @Inject
    public UserService(UserRepository userRepository, EntityManager entityManager) {
        this._userRepository = userRepository;
        this.entityManager = entityManager;
    }

    @Transactional
    public User createNewUser(String name, String username, String email, String password, boolean enabled) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        password = bCryptPasswordEncoder.encode(password);
        User user = new User(name, username, email, password, enabled);
        entityManager.persist(user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = _userRepository.findOneByUsername(username);

        if(userDetails == null) {
            throw new UsernameNotFoundException("Cannot find that user");
        }

        return userDetails;
    }
}
