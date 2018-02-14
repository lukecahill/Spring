package com.lukecahill.spring.bindingmodels;

import org.hibernate.validator.constraints.NotBlank;

public class RolesBindingModels {

    @NotBlank
	public String roleName;

	public RolesBindingModels() {}

	public RolesBindingModels(String roleName) {
		this.roleName = roleName;
	}
}
