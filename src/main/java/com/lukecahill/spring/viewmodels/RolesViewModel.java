package com.lukecahill.spring.viewmodels;

import com.lukecahill.spring.models.Roles;

public class RolesViewModel {
    private int roleId;
    private String roleName;

    public RolesViewModel() {}

    public RolesViewModel(Roles role) {
        this.roleId = role.getRoleId();
        this.roleName = role.getRoleName();
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
