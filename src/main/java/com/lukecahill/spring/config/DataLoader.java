package com.lukecahill.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.lukecahill.spring.models.User;
import com.lukecahill.spring.repositories.UserRepository;

public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    public DataLoader() {
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(new User("luke", "luke", "lmc@outlook.com",
                "$2a$04$hW1MnBIdgNI/4FCW/33h7eB3RNJ5OkDP6VgVm2p6Oi1VcyfVmr2nu", true));
    }
}
