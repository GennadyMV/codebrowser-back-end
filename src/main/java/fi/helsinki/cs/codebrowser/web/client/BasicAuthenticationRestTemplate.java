package fi.helsinki.cs.codebrowser.web.client;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpHost;

import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class BasicAuthenticationRestTemplate extends RestTemplate implements RestOperations {

    public BasicAuthenticationRestTemplate() {

        super();
        setRequestFactory(new HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication());
    }

    public void setCredentials(final HttpHost host,
                               final String username,
                               final String password) {

        final HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication requestFactory = (HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication) getRequestFactory();
        requestFactory.setCredentials(host, username, password);
    }

    public void setBaseUrl(final String baseUrl) throws URISyntaxException {

        final HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication requestFactory = (HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication) getRequestFactory();
        requestFactory.setBaseUrl(baseUrl);
    }

    public String fetchJson(final String url, final String... parameters) throws IOException {

        final HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication requestFactory = (HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication) getRequestFactory();

        for (String parameter : parameters) {
            final String[] split = parameter.split("=");
            requestFactory.addParameter(split[0], split[1]);
        }

        return getForObject(url, String.class);
    }
}
