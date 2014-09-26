package fi.helsinki.cs.codebrowser.web.client;

import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.apache.http.HttpHost;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class TmcApiRestTemplate extends BasicAuthenticationRestTemplate {

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
            throw new IllegalArgumentException();
        }
    }
}
