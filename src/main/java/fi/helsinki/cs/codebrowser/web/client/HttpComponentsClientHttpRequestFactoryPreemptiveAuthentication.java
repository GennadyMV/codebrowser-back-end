package fi.helsinki.cs.codebrowser.web.client;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication extends HttpComponentsClientHttpRequestFactory {

    private final BasicHttpContext context;
    private final AuthCache cache;
    private final CredentialsProvider credentialsProvider;
    private URL baseUrl;

    public HttpComponentsClientHttpRequestFactoryPreemptiveAuthentication() {

        super();

        context = new BasicHttpContext();
        cache = new BasicAuthCache();
        credentialsProvider = new BasicCredentialsProvider();

        context.setAttribute(HttpClientContext.AUTH_CACHE, cache);
    }

    public void setCredentials(final HttpHost host,
                               final String username,
                               final String password) {

        final AuthScheme scheme = new BasicScheme();

        cache.put(host, scheme);

        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        credentialsProvider.setCredentials(new AuthScope(host), credentials);

        setHttpClient(HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build());
    }

    public void setBaseUrl(final String baseUrl) throws MalformedURLException {

        this.baseUrl = new URL(baseUrl);
    }

    @Override
    protected HttpContext createHttpContext(final HttpMethod httpMethod, final URI uri) {

        return context;
    }

    @Override
    public ClientHttpRequest createRequest(final URI uri, final HttpMethod httpMethod) throws IOException {

        final URI resolvedUri;

        try {
            resolvedUri = new URL(baseUrl, uri.getPath()).toURI();
        } catch (URISyntaxException exception) {
            return super.createRequest(uri, httpMethod);
        }

        return super.createRequest(resolvedUri, httpMethod);
    }
}
