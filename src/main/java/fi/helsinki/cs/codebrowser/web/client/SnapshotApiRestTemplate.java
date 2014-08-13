package fi.helsinki.cs.codebrowser.web.client;

import java.net.MalformedURLException;
import javax.annotation.PostConstruct;

import org.apache.http.HttpHost;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SnapshotApiRestTemplate extends BasicAuthenticationRestTemplate {

    @Value("${snapshot.api.host}")
    private String hostname;

    @Value("${snapshot.api.port}")
    private int port;

    @Value("${snapshot.api.username}")
    private String username;

    @Value("${snapshot.api.password}")
    private String password;

    @Value("${snapshot.api.url}")
    private String baseUrl;

    @PostConstruct
    public void initialise() {

        setCredentials(new HttpHost(hostname, port), username, password);

        try {
            setBaseUrl(baseUrl);
        } catch (MalformedURLException exception) {
            return;
        }
    }
}
