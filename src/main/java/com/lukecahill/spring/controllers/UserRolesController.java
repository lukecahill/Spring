package com.lukecahill.spring.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukecahill.spring.bindingmodels.UserRolesBindingModels;
import com.lukecahill.spring.services.UserRolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/api/UserRoles")
public class UserRolesController {

    UserRolesService userRolesService;
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    public UserRolesController() {}

    @Inject
    public UserRolesController(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userRoleId}")
    public ResponseEntity<?> get(@PathVariable int userRoleId) {
        return ResponseEntity.ok(userRolesService.get(userRoleId));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userRolesService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody UserRolesBindingModels userRolesBindingModels, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(gson.toJson(errors.getAllErrors()));
        }

        return ResponseEntity.ok(userRolesService.add(userRolesBindingModels));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{userRoleId}")
    public ResponseEntity<?> update(@PathVariable int userRoleId, @RequestBody UserRolesBindingModels userRolesBindingModels, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(gson.toJson(errors.getAllErrors()));
        }

        return ResponseEntity.ok(userRolesService.update(userRoleId, userRolesBindingModels));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{userRoleId}")
    public ResponseEntity<?> delete(@PathVariable int userRoleId) {
        return null;
    }
}
