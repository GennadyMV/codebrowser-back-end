package fi.helsinki.cs.codebrowser.app;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.Assert.assertEquals;

public final class TokenBasedBasicAuthenticationEntryPointTest {

    @Test
    public void shouldReturnUnauthorized() throws IOException, ServletException {

        final TokenBasedBasicAuthenticationEntryPoint entryPoint = new TokenBasedBasicAuthenticationEntryPoint();
        final MockHttpServletResponse response = new MockHttpServletResponse();

        entryPoint.commence(new MockHttpServletRequest(), response, new BadCredentialsException("Bad credentials"));

        assertEquals(response.getStatus(), 401);
        assertEquals(response.getErrorMessage(), "Bad credentials");
    }
}
