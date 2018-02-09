package com.lukecahill.spring.bindingmodels;

public class RolesBindingModels {
	public String roleName;
	public int roleId;

	public RolesBindingModels() {}

	public RolesBindingModels(int roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}
}
