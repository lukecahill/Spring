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
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class Tests {

    @Inject
    private UserService userService;

    @Inject
    private RolesRepository rolesRepository;

    private User luke = new User("luke", "luke", "luke",
            "", true);

    @Test
    public void loadUserByName_loadUser_shouldWork() {
        UserDetails user = userService.loadUserByUsername("luke");
        Assert.assertEquals(luke.getUsername(), user.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_loadUser_shouldThrowUsernameNotFoundException() {
        userService.loadUserByUsername("notFound");
    }

    @Test
    public void rolesFindOne_findRole_ShouldWork() {
        Roles role = rolesRepository.findOne(1);
        Roles mockRole = new Roles(1, "READ_WRITE");
        Assert.assertEquals(mockRole.getRoleName(), role.getRoleName());
    }

    @Test
    public void rolesFindOne_findRole_ShouldBeNull() {
        Roles role = rolesRepository.findOne(0);
        Assert.assertEquals(null, role);
    }

    @Test
    public void rolesFindOne_findRole_ShouldReturnNull() {
        Roles role = rolesRepository.findOne(0);
        Assert.assertEquals(null, role);
    }
}
