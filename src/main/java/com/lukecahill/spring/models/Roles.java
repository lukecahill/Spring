package com.lukecahill.spring.models;

import com.lukecahill.spring.bindingmodels.RolesBindingModels;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @NotNull
    private String roleName;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<UserRoles> roles;

    public Roles() {}

    public Roles(RolesBindingModels rolesBindingModels) {
        this.roleId = rolesBindingModels.roleId;
        this.roleName = rolesBindingModels.roleName;
    }

    public Roles(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<UserRoles> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<UserRoles> roles) {
        this.roles = roles;
    }
}
