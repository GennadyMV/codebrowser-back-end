package fi.helsinki.cs.codebrowser.web.client;

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
}
