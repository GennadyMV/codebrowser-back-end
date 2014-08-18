package fi.helsinki.cs.codebrowser.web.client;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.apache.http.HttpHost;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TmcApiRestTemplate extends BasicAuthenticationRestTemplate {

    @Value("${tmc.api.host}")
    private String hostname;

    @Value("${tmc.api.port}")
    private int port;

    @Value("${tmc.api.username}")
    private String username;

    @Value("${tmc.api.password}")
    private String password;

    @Value("${tmc.api.url}")
    private String baseUrl;

    @PostConstruct
    public void initialise() {

        setCredentials(new HttpHost(hostname, port), username, password);

        try {
            setBaseUrl(baseUrl);
        } catch (URISyntaxException exception) {
            return;
        }
    }

    public String fetchJson(final String url, final String... parameters) throws IOException {

        final HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication requestFactory =
              (HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication) getRequestFactory();

        for (String parameter : parameters) {
            final String[] split = parameter.split("=");

            requestFactory.addParameter(split[0], split[1]);
        }

        final String json = getForObject(url, String.class);
        return json;
    }
}
