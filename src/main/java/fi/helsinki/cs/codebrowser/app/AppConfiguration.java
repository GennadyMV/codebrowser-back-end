package fi.helsinki.cs.codebrowser.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
public class AppConfiguration {

    @Bean
    public RequestContextFilter requestContextFilter() {

        return new RequestContextFilter();
    }
}
