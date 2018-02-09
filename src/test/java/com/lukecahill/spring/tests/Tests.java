package com.lukecahill.spring.tests;

import com.lukecahill.spring.bindingmodels.UserBindingModel;
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
import org.springframework.security.test.context.support.WithMockUser;
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

    @Test
    @WithMockUser(username = "testuser", roles = { "ADMIN" })
    public void ifUserUpdateIsValid_userShouldBeUpdated() {
        UserBindingModel.Update updatedUser = new UserBindingModel.Update();
        updatedUser.email = "updated@example.com";
        updatedUser.enabled = false;
        updatedUser.name = "updated";
        updatedUser.username = "testuser";
        User updated = userService.update("testuser", updatedUser);

        Assert.assertEquals(updatedUser.email, updated.getEmail());
        Assert.assertEquals(updatedUser.enabled, updated.isEnabled());
        Assert.assertEquals(updatedUser.name, updated.getName());
    }

    @Test
    @WithMockUser(username = "testuser", roles = { "ADMIN" })
    public void ifUserPasswordUpdateIsValid_userPasswordShouldBeUpdated() {
        UserBindingModel.Update updatedUser = new UserBindingModel.Update();
        updatedUser.password = "$2a$04$.9KuvEVK06bEZgz5d1YeNOIQYNRqJ3CnMjAw8UeA8Xl79K8R4SwWS";
        updatedUser.username = "testuser";
        User updated = userService.updatePassword("testuser", updatedUser);

        Assert.assertEquals(updatedUser.password, updated.getPassword());
    }

    @Test(expected = NullPointerException.class)
    public void ifUserUpdateUsernameIsNotValid_userShouldReturnNull() {
        UserBindingModel.Update updatedUser = new UserBindingModel.Update();
        updatedUser.email = "updated@example.com";
        updatedUser.enabled = false;
        updatedUser.name = "updated";
        updatedUser.username = "testuser";
        User updated = userService.update("testuser", updatedUser);

        Assert.assertEquals(null, updated); // this will throw
    }

    @Test(expected = NullPointerException.class)
    public void ifUserPasswordUpdateUsernameIsNotValid_userPasswordShouldReturnNull() {
        UserBindingModel.Update updatedUser = new UserBindingModel.Update();
        updatedUser.username = "testuser";
        updatedUser.password = "$2a$04$.9KuvEVK06bEZgz5d1YeNOIQYNRqJ3CnMjAw8UeA8Xl79K8R4SwWS";
        User updated = userService.updatePassword("testuser", updatedUser);
        Assert.assertEquals(null, updated); // this will throw
    }
}
