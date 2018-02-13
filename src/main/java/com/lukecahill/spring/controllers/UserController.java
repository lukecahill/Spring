package com.lukecahill.spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukecahill.spring.bindingmodels.UserBindingModel;
import com.lukecahill.spring.models.User;
import com.lukecahill.spring.services.UserService;
import com.lukecahill.spring.viewmodels.UserViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/Users")
public class UserController {

    private UserService userService;

    public UserController() {}

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserBindingModel.Create user, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(gson.toJson(errors.getAllErrors()));
        }
        User newUser = userService.createNewUser(user.name, user.username, user.email, user.password, user.enabled);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<UserViewModel> getUsers() {
        return userService.getAll().stream().map(x -> new UserViewModel(x))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value = "/{username}")
    public @ResponseBody ResponseEntity<?> update(@PathVariable String username, @Valid @RequestBody UserBindingModel.Update user, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(gson.toJson(errors.getAllErrors()));
        }
        User updatedUser = userService.update(username, user);
        return ResponseEntity.ok(new UserViewModel(updatedUser));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json", value = "/password/{username}")
    public @ResponseBody ResponseEntity<?> updatePassword(@PathVariable String username, @Valid @RequestBody UserBindingModel.Update user, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(gson.toJson(errors.getAllErrors()));
        }
        User updatedUser = userService.updatePassword(username, user);
        return ResponseEntity.ok(new UserViewModel(updatedUser));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable String username, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(gson.toJson(errors.getAllErrors()));
        }

        // should we be able to remove users?
        userService.delete(username);
        return ResponseEntity.ok(username);
    }
}
