package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class DefaultAuthenticationServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void shouldReturnNullIfUnauthorized() {

        assertNull(authenticationService.currentUser());
    }

    @Test
    public void shouldReturnCurrentUserIfAuthorized() throws IOException, ServletException {

        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("passwordpassword");

        user = userRepository.save(user);

        SecurityContextHolder.getContext()
                             .setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        assertEquals(authenticationService.currentUser().getUsername(), user.getUsername());

        userRepository.delete(user);
    }

    @Test
    public void shouldReturnNothingAfterInvalidation() {

        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword("passwordpassword");

        user = userRepository.save(user);

        SecurityContextHolder.getContext()
                             .setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        assertEquals(authenticationService.currentUser(), user);

        authenticationService.invalidate();

        assertNull(authenticationService.currentUser());

        userRepository.delete(user);
    }
}
