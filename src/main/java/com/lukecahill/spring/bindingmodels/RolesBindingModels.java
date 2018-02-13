package com.lukecahill.spring.bindingmodels;

import javax.validation.constraints.NotNull;

public class RolesBindingModels {
    
    @NotNull
	public String roleName;
    @NotNull
	public int roleId;

	public RolesBindingModels() {}

	public RolesBindingModels(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
}
