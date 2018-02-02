package com.lukecahill.spring.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    private String roleName;

    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<UserRoles> roles;

    public Roles() {}

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
