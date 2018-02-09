package com.lukecahill.spring.bindingmodels;

import com.lukecahill.spring.models.User;

public class UserBindingModel {
	public String username;
	public String name;
	public String password;
	public String email;
	public boolean enabled;

	public UserBindingModel() {}

	public UserBindingModel(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.name = user.getName();
		this.email = user.getEmail();
		this.enabled = user.isEnabled();
	}

	public UserBindingModel(String username, String name, String password, String email, boolean enabled) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}
}
