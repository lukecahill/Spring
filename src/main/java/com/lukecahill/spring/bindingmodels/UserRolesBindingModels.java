package com.lukecahill.spring.bindingmodels;

public class UserRolesBindingModels {

	public String username;
	public int roleId;

	public UserRolesBindingModels() {}

	public UserRolesBindingModels(String username, int roleId) {
		this.roleId = roleId;
		this.username = username;
	}
}
