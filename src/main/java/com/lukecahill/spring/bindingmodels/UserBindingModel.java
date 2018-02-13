package com.lukecahill.spring.bindingmodels;

import javax.validation.constraints.NotNull;

public class UserBindingModel {

	public static class Create {

		@NotNull
		public String username;
		@NotNull
		public String name;
		@NotNull
		public String password;
		@NotNull
		public String email;
		public boolean enabled;
	}

	public static class Update {

		@NotNull
		public String username;
		@NotNull
		public String name;
		@NotNull
		public String password;
		@NotNull
		public String email;
		public boolean enabled;
	}
}
