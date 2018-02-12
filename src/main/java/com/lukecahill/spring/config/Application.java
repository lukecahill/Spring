package com.lukecahill.spring.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan("com.lukecahill.spring.models")
@EnableJpaRepositories("com.lukecahill.spring.repositories")
@EnableWebMvc
@ComponentScan(basePackages = "com.lukecahill.spring")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
