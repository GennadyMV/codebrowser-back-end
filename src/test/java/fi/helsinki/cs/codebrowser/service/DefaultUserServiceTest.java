package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@ActiveProfiles("test")
@WebAppConfiguration
public class DefaultUserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void serviceReturnsNullOnNonexistantUser() {

        assertNull(userService.findByUsername("no such username"));
    }

    @Test
    public void serviceReturnsUserOnExistingUser() {

        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password1234512335");

        user = userRepository.save(user);

        assertEquals(user, userService.findByUsername("testUser"));
    }
}
