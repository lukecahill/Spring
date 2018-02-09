package com.lukecahill.spring.services;

import com.lukecahill.spring.bindingmodels.RolesBindingModels;
import com.lukecahill.spring.models.Roles;
import com.lukecahill.spring.repositories.RolesRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class RolesService {

    private RolesRepository rolesRepository;
    private EntityManager entityManager;

    @Inject
    public RolesService(RolesRepository rolesRepository, EntityManager entityManager) {
        this.entityManager = entityManager;
        this.rolesRepository = rolesRepository;
    }

    public Roles get(int roleId) {
        return rolesRepository.getOne(roleId);
    }

    public List<Roles> getAll() {
        return rolesRepository.findAll();
    }

    public Roles add(RolesBindingModels role) {
        Roles roleToAdd = new Roles(role);
        return rolesRepository.save(roleToAdd);
    }
}
