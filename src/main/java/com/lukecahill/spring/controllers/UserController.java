package com.lukecahill.spring.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/Users")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public String getUsername() {

        return "luke";
    }
}
