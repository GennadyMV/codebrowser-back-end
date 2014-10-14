package fi.helsinki.cs.codebrowser.app;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public RequestContextFilter requestContextFilter() {

        return new RequestContextFilter();
    }

    @Override
    public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {

        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> messageConverters) {

        messageConverters.add(new MappingJackson2HttpMessageConverter());
        messageConverters.add(new ByteArrayHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new PlainErrorMessageConverter());
    }
}
