package fi.helsinki.cs.codebrowser.app.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public final class SimpleCORSFilterTest {

    private ServletRequest servletRequest;
    private HttpServletResponse servletResponse;
    private FilterChain filterChain;

    private SimpleCORSFilter filter;

    @Before
    public void setUp() {

        servletRequest = mock(ServletRequest.class);
        servletResponse = mock(HttpServletResponse.class);
        filterChain = mock(FilterChain.class);

        filter = new SimpleCORSFilter();
    }

    @Test
    public void setsCorrectHeaders() throws IOException, ServletException {

        filter.doFilter(servletRequest, servletResponse, filterChain);

        verify(servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        verify(servletResponse).setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
        verify(servletResponse).setHeader("Access-Control-Allow-Methods", "OPTIONS, DELETE");
        verify(servletResponse).setHeader("Access-Control-Expose-Headers", "X-Authentication-Token");
    }

    @Test
    public void passesOnwardsAfterFilter() throws ServletException, IOException {

        filter.doFilter(servletRequest, servletResponse, filterChain);

        verify(filterChain).doFilter(servletRequest, servletResponse);
    }
}
