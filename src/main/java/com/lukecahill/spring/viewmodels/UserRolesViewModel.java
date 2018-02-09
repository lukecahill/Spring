package com.lukecahill.spring.viewmodels;

import com.lukecahill.spring.models.UserRoles;

public class UserRolesViewModel {

    private int roleId;
    private String username;
    private String roleName;

    public UserRolesViewModel() {}

    public UserRolesViewModel(UserRoles userRoles) {
        this.roleId = userRoles.getUserRoleId();
        this.username = userRoles.getUsername();
        this.roleName = userRoles.getRole();
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
