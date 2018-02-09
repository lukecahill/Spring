package com.lukecahill.spring.bindingmodels;

public class UserBindingModel {

	public static class Create {

		public String username;
		public String name;
		public String password;
		public String email;
		public boolean enabled;
	}

	public static class Update {

		public String username;
		public String name;
		public String password;
		public String email;
		public boolean enabled;
	}
}
