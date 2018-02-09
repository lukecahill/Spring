package com.lukecahill.spring.controllers;

import com.lukecahill.spring.bindingmodels.RolesBindingModels;
import com.lukecahill.spring.repositories.RolesRepository;
import com.lukecahill.spring.viewmodels.RolesViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/Roles")
public class RolesController {

    private RolesRepository rolesRepository;

    @Inject
    public RolesController(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{roleId}")
    public ResponseEntity<?> get(@PathVariable int roleId) {
        return ResponseEntity.ok(new RolesViewModel(rolesRepository.findOne(roleId)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<RolesViewModel> roles = rolesRepository.findAll().stream()
                .map(x -> new RolesViewModel(x)).collect(Collectors.toCollection(ArrayList::new));
        return ResponseEntity.ok(roles);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody RolesBindingModels role) {
        return ResponseEntity.ok(null);
    }
}
