package fi.helsinki.cs.codebrowser.web.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.exception.NotFoundException;

import java.net.URISyntaxException;

import org.apache.http.HttpHost;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.junit.Assert.assertEquals;

public class BasicAuthenticationRestTemplateTest {

    private static final String PATH = "/test.json";
    private static final String ANY = ".*";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private BasicAuthenticationRestTemplate template;

    @Before
    public void setUp() throws URISyntaxException {

        template = new BasicAuthenticationRestTemplate();

        stubFor(get(urlEqualTo(PATH)).willReturn(aResponse().withBody("")));

        template.setBaseUrl("http://localhost:8089");
        template.setCredentials(new HttpHost("localhost", 8089), "username", "password");
    }


    @Test
    public void fetchJsonSetsCorrectBasicAuthHeader() {

        template.fetchJson(PATH);

        verify(getRequestedFor(urlEqualTo(PATH))
                              .withHeader("Authorization", equalTo("Basic dXNlcm5hbWU6cGFzc3dvcmQ=")));
    }

    @Test
    public void fetchJsonSetsHeaderToAcceptApplicationJson() {

        template.fetchJson(PATH);

        verify(getRequestedFor(urlEqualTo(PATH))
                              .withHeader("Accept", matching(".*application/json.*")));
    }

    @Test
    public void fetchJsonSetsCorrectParameters() {

        stubFor(get(urlMatching(PATH + ANY)).willReturn(aResponse().withBody("")));

        template.fetchJson(PATH, "other_header=1", "api_version=7");

        verify(getRequestedFor(urlMatching(ANY + PATH + ANY)));
        verify(getRequestedFor(urlMatching(ANY + "api_version=7" + ANY)));
        verify(getRequestedFor(urlMatching(ANY + "other_header=1" + ANY)));
    }

    @Test(expected = NotFoundException.class)
    public void fetchJsonThrowsNotFoundExceptionWhenServerRespondsWithNotFound() {

        stubFor(get(urlEqualTo(PATH)).willReturn(aResponse().withStatus(404)));

        template.fetchJson(PATH);
    }

    @Test(expected = HttpStatusCodeException.class)
    public void fetchJsonThrowsHttpStatusCodeExceptionWithMatchingStatusCodeWhenServerRespondsWithInternalServerError() {

        stubFor(get(urlEqualTo(PATH)).willReturn(aResponse().withStatus(500)));

        try {
            template.fetchJson(PATH);
        } catch (HttpStatusCodeException ex) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ex.getStatusCode());
            throw ex;
        }
    }

    @Test
    public void canAddParameters() {

        stubFor(get(urlEqualTo(PATH + "?param=value")).willReturn(aResponse().withBody("")));

        template.addParameter("param", "value");

        template.fetchJson(PATH);

        verify(getRequestedFor(urlEqualTo(PATH + "?param=value")));
    }
}
