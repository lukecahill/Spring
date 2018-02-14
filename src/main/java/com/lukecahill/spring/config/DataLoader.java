package com.lukecahill.spring.config;

import com.lukecahill.spring.models.Game;
import com.lukecahill.spring.models.Roles;
import com.lukecahill.spring.models.UserRoles;
import com.lukecahill.spring.repositories.GameRepository;
import com.lukecahill.spring.repositories.RolesRepository;
import com.lukecahill.spring.repositories.UserRolesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.lukecahill.spring.models.User;
import com.lukecahill.spring.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private UserRolesRepository userRolesRepository;
    private GameRepository gameRepository;

    @Value("${sql.initial.run}")
    private String initialRun;

    public DataLoader() {
    }

    @Inject
    public DataLoader(UserRepository userRepository, RolesRepository rolesRepository, UserRolesRepository userRolesRepository, GameRepository gameRepository) {
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.userRolesRepository = userRolesRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if("true".equals(initialRun)) {

            // creates a user with the username 'luke' and password 'password'
            // also creates roles, and assigns luke the admin role.
            User user = new User("luke", "luke", "lmc@outlook.com",
                    "$2a$04$hW1MnBIdgNI/4FCW/33h7eB3RNJ5OkDP6VgVm2p6Oi1VcyfVmr2nu", true);
            userRepository.save(user);

            rolesRepository.save(new Roles(1, "READ_WRITE"));
            rolesRepository.save(new Roles(2, "READ"));
            Roles adminRole = new Roles(3, "ADMIN");
            rolesRepository.save(adminRole);

            userRolesRepository.save(new UserRoles(1, user, adminRole));

            gameRepository.save(new Game(1, "Rainbow Six: Siege", "Ubisoft", 12.99D));
            gameRepository.save(new Game(2, "Total War: Britannia", "Sega", 39.99D));
        }
    }
}
