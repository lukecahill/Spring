package com.lukecahill.spring.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserRoles")
public class UserRoles implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Roles role;

    public UserRoles() {}

    public UserRoles(int userRoleId, User username, Roles role) {
        this.userRoleId = userRoleId;
        this.username = username;
        this.role = role;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getUsername() {
        return username.getUsername();
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public String getRole() {
        return role.getRoleName();
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role.getRoleName();
    }
}
