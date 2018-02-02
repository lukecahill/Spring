package com.lukecahill.spring.controllers;

import com.lukecahill.spring.models.User;
import com.lukecahill.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/Users")
public class UserController {

    @Autowired
    private UserService _userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUsername() {

        return "luke";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/Create")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        User newUser = _userService.createNewUser(user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.isEnabled());
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
