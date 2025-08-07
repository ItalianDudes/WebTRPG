package it.italiandudes.webtrpg.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EntityScan(basePackages = "it.italiandudes.webtrpg")
@ComponentScan(basePackages = "it.italiandudes.webtrpg")
@EnableJpaRepositories(basePackages = "it.italiandudes.webtrpg")
@EnableScheduling
public class WebTRPG {

    // Spring Application Starter
    public static void main(String[] args) {
        SpringApplication.run(WebTRPG.class, args);
    }
}
