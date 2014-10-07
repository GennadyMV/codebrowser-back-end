package fi.helsinki.cs.codebrowser.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class TokenBasedBasicAuthenticationEntryPoint implements InitializingBean, AuthenticationEntryPoint {

    @Override
    public void afterPropertiesSet() throws Exception { }

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException exception) throws IOException,
                                                                         ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    }
}
