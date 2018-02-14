package com.lukecahill.spring.bindingmodels;

import javax.validation.constraints.NotNull;

public class RolesBindingModels {

    @NotNull
	public String roleName;

	public RolesBindingModels() {}

	public RolesBindingModels(String roleName) {
		this.roleName = roleName;
	}
}
