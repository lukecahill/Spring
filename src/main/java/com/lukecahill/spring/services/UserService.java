package com.lukecahill.spring.services;

import com.lukecahill.spring.bindingmodels.UserBindingModel;
import com.lukecahill.spring.models.User;
import com.lukecahill.spring.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private EntityManager entityManager;

    public UserService() {}

    @Inject
    public UserService(UserRepository userRepository, EntityManager entityManager) {
        this.userRepository = userRepository;
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
        UserDetails userDetails = userRepository.findOneByUsername(username);

        if(userDetails == null) {
            throw new UsernameNotFoundException("Cannot find that user");
        }

        return userDetails;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(String username) {
        return userRepository.findOneByUsername(username);
    }

    public User update(String username, UserBindingModel.Update user) {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(username)) {
            return null;
        }

        User foundUser = userRepository.findOneByUsername(username);
        foundUser.setEmail(user.email);
        foundUser.setEnabled(user.enabled);
        foundUser.setName(user.name);

        userRepository.save(foundUser);
        return foundUser;
    }

    public User updatePassword(String username, UserBindingModel.Update user) {
        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(username)) {
            return null;
        }

        User foundUser = userRepository.findOneByUsername(username);
        foundUser.setPassword(user.password);
        return foundUser;
    }
}
