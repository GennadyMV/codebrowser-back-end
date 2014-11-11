package fi.helsinki.cs.codebrowser.app.filter;

import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.service.AuthenticationService;
import fi.helsinki.cs.codebrowser.service.TokenService;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class TokenBasedBasicAuthenticationFilter extends GenericFilterBean {

    private static final String AUTHENTICATION_TOKEN_HEADER = "X-Authentication-Token";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain) throws IOException,
                                                         ServletException {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        User user = authenticationService.currentUser();

        if (httpRequest.getHeader(AUTHENTICATION_TOKEN_HEADER) != null &&
            httpRequest.getHeader(AUTHENTICATION_TOKEN_HEADER).equals("invalidate")) {

            tokenService.invalidate(user);
            authenticationService.invalidate();
            user = authenticationService.currentUser();
        }

        // Return authentication token for user if authenticated
        if (user != null) {
            httpResponse.setHeader(AUTHENTICATION_TOKEN_HEADER, tokenService.token(user));
        }

        chain.doFilter(request, response);
    }
}
