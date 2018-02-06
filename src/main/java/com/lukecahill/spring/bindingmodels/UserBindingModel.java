package com.lukecahill.spring.bindingmodels;

public class UserBindingModel {
	public String username;
	public String name;
	public String password;
	public String email;
	public boolean enabled;

	public UserBindingModel() {}

	public UserBindingModel(String username, String name, String password, String email, boolean enabled) {
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
	}
}
