package fi.helsinki.cs.codebrowser.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("fi.helsinki.cs.codebrowser")
@EnableJpaRepositories("fi.helsinki.cs.codebrowser.repository")
@EntityScan("fi.helsinki.cs.codebrowser.model")
@EnableAutoConfiguration
public class App {

    public static void main(final String[] args) throws Exception {

        SpringApplication.run(App.class, args);
    }
}
