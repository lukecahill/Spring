package com.lukecahill.spring.bindingmodels;

import org.hibernate.validator.constraints.NotBlank;

public class UserBindingModel {

	public static class Create {

		@NotBlank
		public String username;
		@NotBlank
		public String name;
		@NotBlank
		public String password;
		@NotBlank
		public String email;
		public boolean enabled;
	}

	public static class Update {

		@NotBlank
		public String username;
		@NotBlank
		public String name;
		@NotBlank
		public String password;
		@NotBlank
		public String email;
		public boolean enabled;
	}
}
