package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultTokenServiceTest {

    @Autowired
    private TokenService tokenService;

    private User user;

    @Before
    public void setUp() {

        user = new User();
    }

    @Test
    public void generatesTokenForUser() {

        final String token = tokenService.token(user);

        assertNotNull(token);
        assertTrue(token.length() > 0);
    }

    @Test
    public void authenticateReturnsCorrectUserForToken() {

        final String token = tokenService.token(user);

        final User returnedUser = tokenService.authenticate(token);

        assertEquals(user, returnedUser);
    }

    @Test
    public void authenticateReturnsCorrectUserForTokenMultipleTimes() {

        final String token = tokenService.token(user);

        tokenService.authenticate(token);
        final User returnedUser = tokenService.authenticate(token);

        assertEquals(user, returnedUser);
    }

    @Test
    public void tokenReturnsPreviouslyCreatedToken() {

        final String token = tokenService.token(user);
        tokenService.token(user);

        final User returnedUser = tokenService.authenticate(token);

        assertEquals(user, returnedUser);
    }
}
