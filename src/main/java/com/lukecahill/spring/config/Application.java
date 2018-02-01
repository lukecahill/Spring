package com.lukecahill.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.lukecahill.spring.config",
        "com.lukecahill.spring.repositories",
        "com.lukecahill.spring.models",
        "com.lukecahill.spring.services",
        "com.lukecahill.spring.controllers"
})
@EntityScan("com.lukecahill.spring.models")
@EnableJpaRepositories("com.lukecahill.spring.repositories")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
