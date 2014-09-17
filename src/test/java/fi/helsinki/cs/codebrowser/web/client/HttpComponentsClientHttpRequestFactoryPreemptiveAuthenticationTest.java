package fi.helsinki.cs.codebrowser.web.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HttpComponentsClientHttpRequestFactoryPreemptiveAuthenticationTest {

    private static final String BASE_URL = "http://www.example.com";
    private static final String PATH = "/test";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication auth;

    @Before
    public void setUp() {

        auth = new HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication();
    }

    @Test
    public void createHttpContextReturnsNonNullContext() {

        assertNotNull(auth.createHttpContext(HttpMethod.GET, null));
    }

    @Test
    public void createHttpContextReturnsContextWithAuthCache() {

        assertNotNull(auth.createHttpContext(HttpMethod.GET, null).getAttribute(HttpClientContext.AUTH_CACHE));
    }

    @Test
    public void createRequestAppendsUriToBaseUrl() throws IOException, URISyntaxException {

        auth.setBaseUrl(BASE_URL);

        final ClientHttpRequest request = auth.createRequest(new URI("/test"), HttpMethod.GET);

        assertEquals(BASE_URL + PATH, request.getURI().toString());
    }

    @Test
    public void createRequestAddsParametersToRequest() throws URISyntaxException, IOException {

        auth.setBaseUrl(BASE_URL);
        auth.addParameter("testKey1", "testValue1");
        auth.addParameter("testKey2", "testValue2");

        final ClientHttpRequest request = auth.createRequest(new URI("/test"), HttpMethod.GET);

        assertTrue(request.getURI().toString().contains("testKey1=testValue1"));
        assertTrue(request.getURI().toString().contains("testKey2=testValue2"));
    }

    @Test
    public void setCredentialsSetsCredentials() throws URISyntaxException, IOException {

        auth.setBaseUrl("http://localhost:8089");
        auth.setCredentials(new HttpHost("localhost", 8089), "username", "password");

        auth.createRequest(new URI(PATH), HttpMethod.GET).execute();

        verify(getRequestedFor(urlEqualTo(PATH))
                              .withHeader("Authorization", equalTo("Basic dXNlcm5hbWU6cGFzc3dvcmQ=")));
    }
}
