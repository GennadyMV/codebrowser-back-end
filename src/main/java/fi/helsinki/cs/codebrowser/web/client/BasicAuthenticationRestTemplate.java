package fi.helsinki.cs.codebrowser.web.client;

import fi.helsinki.cs.codebrowser.exception.NotFoundException;

import java.net.URISyntaxException;

import org.apache.http.HttpHost;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
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

    public String fetchJson(final String url, final String... parameters) {

        final HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication requestFactory = (HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication) getRequestFactory();

        for (String parameter : parameters) {
            final String[] split = parameter.split("=");
            requestFactory.addParameter(split[0], split[1]);
        }

        return getForObject(url, String.class);
    }

    public void addParameter(final String key, final String value) {

        final HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication requestFactory = (HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication) getRequestFactory();
        requestFactory.addParameter(key, value);
    }

    @Override
    public <T> T getForObject(final String url, final Class<T> responseType, final Object... urlVariables) {

        try {

            return super.getForObject(url, responseType, urlVariables);

        } catch (HttpStatusCodeException exception) {

            if (exception.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
                throw new NotFoundException();
            }

            throw exception;
        }
    }
}
