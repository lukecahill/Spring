package com.lukecahill.spring.services;

import com.lukecahill.spring.bindingmodels.UserRolesBindingModels;
import com.lukecahill.spring.models.Roles;
import com.lukecahill.spring.models.User;
import com.lukecahill.spring.models.UserRoles;
import com.lukecahill.spring.repositories.RolesRepository;
import com.lukecahill.spring.repositories.UserRepository;
import com.lukecahill.spring.repositories.UserRolesRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Service("userRolesService")
public class UserRolesService {

    EntityManager entityManager;
    UserRolesRepository userRolesRepository;
    UserRepository userRepository;
    RolesRepository rolesRepository;

    public UserRolesService() {}

    @Inject
    public UserRolesService(EntityManager entityManager, UserRolesRepository userRolesRepository, UserRepository userRepository, RolesRepository rolesRepository) {
        this.entityManager = entityManager;
        this.userRolesRepository = userRolesRepository;
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }

    public UserRoles add(UserRolesBindingModels userRolesBindingModels) {
        if(userRolesBindingModels == null) {
            return null;
        }

        User user = userRepository.findOneByUsername(userRolesBindingModels.username);
        Roles role = rolesRepository.findOne(userRolesBindingModels.roleId);

        UserRoles userRoleToAdd = new UserRoles(user, role);
        return userRoleToAdd;
    }

    public UserRoles update(int userRoleId, UserRolesBindingModels userRolesBindingModels) {
        if(userRolesBindingModels == null) {
            return null;
        }

        UserRoles userRolesToUpdate = userRolesRepository.findOne(userRoleId);
        return null;
    }

    public UserRoles get(int userRoleId) {
        return userRolesRepository.getOne(userRoleId);
    }

    public List<UserRoles> getAll() {
        return userRolesRepository.findAll();
    }

    public void delete(int userRoleId) {
        userRolesRepository.delete(userRoleId);
    }
}
