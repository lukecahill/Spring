package com.lukecahill.spring.tests;

import com.lukecahill.spring.config.Application;
import com.lukecahill.spring.models.Roles;
import com.lukecahill.spring.models.User;
import com.lukecahill.spring.repositories.RolesRepository;
import com.lukecahill.spring.services.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class Tests {

    @Inject
    private UserService userService;

    @Inject
    private RolesRepository rolesRepository;

    private User testuser = new User("testuser", "testuser", "testuser@example.com",
            "", false);

    @Test
    public void ifCreateNewUser_shouldCreateUser() {
        User createdUser = userService.createNewUser("testuser", "testuser", "testuser@example.com",
                "", false);
        Assert.assertEquals(testuser.getUsername(), createdUser.getUsername());
    }

    @Test
    public void ifLoadUserByNameIsValid_shouldReturnUser() {
        UserDetails user = userService.loadUserByUsername("testuser");
        Assert.assertEquals(testuser.getUsername(), user.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void ifLoadUserByUsernameIsNotFound_shouldThrowUsernameNotFoundException() {
        userService.loadUserByUsername("notFound");
    }

    @Test
    public void ifRolesSave_shouldPersistToDatabase() {
        Roles role = rolesRepository.save(new Roles(4, "TEST_ROLE"));
        Assert.assertEquals("TEST_ROLE", role.getRoleName());
    }

    @Test
    public void ifRolesFindOneIsValid_shouldReturnRole() {
        Roles role = rolesRepository.findOne(1);
        Roles mockRole = new Roles(1, "READ_WRITE");
        Assert.assertEquals(mockRole.getRoleName(), role.getRoleName());
    }

    @Test
    public void ifRolesFindOneIsNotFound_shouldBeNull() {
        Roles role = rolesRepository.findOne(0);
        Assert.assertEquals(null, role);
    }
}
