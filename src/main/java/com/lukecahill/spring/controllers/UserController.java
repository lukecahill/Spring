package com.lukecahill.spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukecahill.spring.bindingmodels.UserBindingModel;
import com.lukecahill.spring.models.User;
import com.lukecahill.spring.services.UserService;
import com.lukecahill.spring.viewmodels.UserViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/Users")
public class UserController {

    private UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    @RequestMapping(method = RequestMethod.GET, value = "/{username}", produces = "application/json")
    public @ResponseBody UserViewModel get(@PathVariable String username) {
        User user = userService.get(username);
        return new UserViewModel(user);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/Create")
    public ResponseEntity<?> createNewUser(@RequestBody UserBindingModel.Create user) {
        User newUser = userService.createNewUser(user.name, user.username, user.email, user.password, user.enabled);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UserViewModel> getUsers() {
        return userService.getAll().stream().map(x -> new UserViewModel(x))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value = "/{username}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable String username, UserBindingModel.Update user) {
        User updatedUser = userService.update(username, user);
        return ResponseEntity.ok(new UserViewModel(updatedUser));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value = "/password/{username}")
    public @ResponseBody ResponseEntity<?> updatePassword(@PathVariable String username, UserBindingModel.Update user) {
        User updatedUser = userService.updatePassword(username, user);
        return ResponseEntity.ok(new UserViewModel(updatedUser));
    }
}
