package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Role;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultUserDetailsServiceTest {

    private static final String AUTHORISATION_HEADER = "Authorization";

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Before
    public void setUp() {

        user = new User();
        user.setUsername("username");
        user.setPassword("passwordpassword");

        final Role role = new Role();
        role.setRolename("user");

        final List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        user = userRepository.save(user);
    }

    @After
    public void tearDown() {

        userRepository.delete(user);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowExceptionOnNonExistentUsername() {

        userRepository.delete(user);
        userDetailsService.loadUserByUsername("username");
    }

    @Test
    public void shouldLoadUserByUsername() {

        final UserDetails userDetails = userDetailsService.loadUserByUsername("username");

        assertEquals(userDetails.getUsername(), "username");
        assertEquals(userDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority(), "user");
    }

    @Test
    public void shouldLoadUserByToken() {

        final MockHttpServletRequest request = new MockHttpServletRequest();

        final String token = ":" + tokenService.token(user);
        final String base64Token = Base64.encodeBase64String(token.getBytes());

        request.addHeader(AUTHORISATION_HEADER, "Basic " + base64Token);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        final UserDetails userDetails = userDetailsService.loadUserByUsername("");

        assertEquals(userDetails.getUsername(), "username");
        assertEquals(userDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority(), "user");
    }
}
