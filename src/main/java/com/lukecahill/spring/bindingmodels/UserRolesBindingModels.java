package com.lukecahill.spring.bindingmodels;

import org.hibernate.validator.constraints.NotBlank;

public class UserRolesBindingModels {

	@NotBlank
	public String username;

	@NotBlank
	public int roleId;

	public UserRolesBindingModels() {}

	public UserRolesBindingModels(String username, int roleId) {
		this.roleId = roleId;
		this.username = username;
	}
}
