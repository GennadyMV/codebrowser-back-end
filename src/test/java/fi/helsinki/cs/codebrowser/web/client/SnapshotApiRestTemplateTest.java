package fi.helsinki.cs.codebrowser.web.client;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.test.util.ReflectionTestUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class SnapshotApiRestTemplateTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private SnapshotApiRestTemplate template;

    @Before
    public void setUp() {

        template = new SnapshotApiRestTemplate();

        injectFields();
        template.initialise();

        stubFor(get(urlMatching(".*")).willReturn(aResponse().withBody("")));
    }

    private void injectFields() {

        setField("hostname", "localhost");
        setField("port", 8089);
        setField("username", "username");
        setField("password", "password");
        setField("baseUrl", "http://localhost:8089");
    }

    private void setField(final String fieldName, final Object value) {

        ReflectionTestUtils.setField(template, fieldName, value);
    }

    @Test
    public void initialisationSetsValues() {

        template.fetchJson("/test");

        verify(getRequestedFor(urlEqualTo("/test"))
                .withHeader("Authorization", equalTo("Basic dXNlcm5hbWU6cGFzc3dvcmQ=")));
    }
}
