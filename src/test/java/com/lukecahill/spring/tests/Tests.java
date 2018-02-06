package com.lukecahill.spring.tests;

import com.lukecahill.spring.models.User;
import com.lukecahill.spring.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;

public class Tests {

	@Inject
	private UserService userService;

	User luke = new User("luke", "luke", "luke", "luke", true);

	@Test
	public void loadUserByName_loadUser_shouldWork() {
		UserDetails user = userService.loadUserByUsername("luke");

		Assert.assertEquals(luke.getName(), user.getUsername());
	}

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsername_loadUser_shouldFail() {
		UserDetails userDetails = userService.loadUserByUsername("notFound");
	}

}
