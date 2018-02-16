package com.lukecahill.spring.tests;

import com.lukecahill.spring.bindingmodels.GameBindingModel;
import com.lukecahill.spring.bindingmodels.UserBindingModel;
import com.lukecahill.spring.config.Application;
import com.lukecahill.spring.models.Game;
import com.lukecahill.spring.models.Roles;
import com.lukecahill.spring.models.User;
import com.lukecahill.spring.repositories.RolesRepository;
import com.lukecahill.spring.services.GameService;
import com.lukecahill.spring.services.UserService;
import com.lukecahill.spring.viewmodels.GameViewModel;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

    @Inject
    private UserService userService;

    @Inject
    private RolesRepository rolesRepository;

    @Inject
    private GameService gameService;

    private User testuser = new User("testuser", "testuser", "testuser@example.com",
            "", false);
    private Game testGame = new Game(10, "Rome: Total War", "Sega", 3.99D);
    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

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
        List<Roles> roles = rolesRepository.findAll();
        Roles role = rolesRepository.findOne(1);
        Roles mockRole = new Roles(1, "TEST_ROLE");
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

    @Test
    public void ifGameAddIsValid_gameShouldBeAddedToDatabase() {
        GameBindingModel.Create gameToAdd = new GameBindingModel.Create( "Rome: Total War", "Sega", 3.99D);
        Game addedGame = gameService.add(gameToAdd);

        Assert.assertEquals(gameToAdd.gameName, addedGame.getName());
        Assert.assertEquals(gameToAdd.gamePrice, addedGame.getPrice(), 0.1D);
        Assert.assertEquals(gameToAdd.gamePublisher, addedGame.getPublisher());
    }

    @Test
    public void ifGameGetIsValid_gameShouldBeRetrievedFromDatabase() {
        Game retrievedGame = gameService.get(1);
        Assert.assertEquals(testGame.getName(), retrievedGame.getName());
    }

    @Test(expected = NullPointerException.class)
    public void ifGameGetIsInvalid_shouldThrowNull() {
        Game retrievedGame = gameService.get(0);
        Assert.assertEquals(testGame.getName(), retrievedGame.getName());
    }

    @Test
    public void ifGameUpdateIsValid_gameShouldBeEditedInDatabase() {
        GameBindingModel.Update gameToUpdate = new GameBindingModel.Update();
        gameToUpdate.gamePublisher = "test";
        gameToUpdate.gameName = "test";
        gameToUpdate.gamePrice = 45D;

        Game updated = gameService.update(1, gameToUpdate);
        Assert.assertEquals(gameToUpdate.gameName, updated.getName());
        Assert.assertEquals(gameToUpdate.gamePublisher, updated.getPublisher());
        Assert.assertEquals(gameToUpdate.gamePrice, updated.getPrice(), 0.1D);
    }

    @Test
    public void ifGameAddIsInvalid_gameValidationShouldFail() {
        Game gameToFail = new Game(0, "", "", 10.0D);
        Set<ConstraintViolation<Game>> violations = validator.validate(gameToFail);
        Assert.assertFalse(violations.isEmpty());
    }
}
