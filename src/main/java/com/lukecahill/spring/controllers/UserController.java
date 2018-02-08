package com.lukecahill.spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukecahill.spring.models.User;
import com.lukecahill.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/Users")
public class UserController {

    private UserService _userService;

    @Inject
    public UserController(UserService userService) {
        this._userService = userService;
    }

    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    @RequestMapping(method = RequestMethod.GET, value = "/{username}", produces = "application/json")
    public @ResponseBody String getUsername(@PathVariable String username) {
        UserDetails user = _userService.loadUserByUsername(username);

        return gson.toJson(user.getUsername());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/Create")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        User newUser = _userService.createNewUser(user.getName(), user.getUsername(), user.getEmail(), user.getPassword(), user.isEnabled());
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
