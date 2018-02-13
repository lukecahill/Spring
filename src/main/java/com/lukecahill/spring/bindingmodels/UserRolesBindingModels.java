package com.lukecahill.spring.bindingmodels;

import javax.validation.constraints.NotNull;

public class UserRolesBindingModels {

	@NotNull
	public String username;

	@NotNull
	public int roleId;

	public UserRolesBindingModels() {}

	public UserRolesBindingModels(String username, int roleId) {
		this.roleId = roleId;
		this.username = username;
	}
}
