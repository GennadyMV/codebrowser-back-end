package fi.helsinki.cs.codebrowser.app.filter;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Role;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.UserRepository;
import fi.helsinki.cs.codebrowser.service.TokenService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
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
public final class TokenBasedBasicAuthenticationFilterTest {

    private static final String AUTHENTICATION_TOKEN_HEADER = "X-Authentication-Token";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenBasedBasicAuthenticationFilter filter;

    @Test
    public void shouldNotReturnTokenIfUnauthorized() throws IOException, ServletException {

        final MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(new MockHttpServletRequest(), response, new MockFilterChain());

        assertNull(response.getHeader(AUTHENTICATION_TOKEN_HEADER));
    }

    @Test
    public void shouldReturnTokenIfAuthorized() throws IOException, ServletException {

        User user = new User();
        user.setUsername("username");
        user.setPassword("passwordpassword");

        final Role role = new Role();
        role.setRolename("user");

        final List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);

        user = userRepository.save(user);

        SecurityContextHolder.getContext()
                             .setAuthentication(new UsernamePasswordAuthenticationToken("username", "passwordpassword"));

        final MockHttpServletResponse response = new MockHttpServletResponse();

        filter.doFilter(new MockHttpServletRequest(), response, new MockFilterChain());

        assertEquals(response.getHeader(AUTHENTICATION_TOKEN_HEADER), tokenService.token(user));

        userRepository.delete(user);
    }
}
