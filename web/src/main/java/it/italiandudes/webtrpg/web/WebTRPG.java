package it.italiandudes.webtrpg.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {
        "it.italiandudes.webtrpg.core",
        "it.italiandudes.webtrpg.web",
        "it.italiandudes.webtrpg.fallout"
})
public class WebTRPG {

    // Spring Application Starter
    public static void main(String[] args) {
        SpringApplication.run(WebTRPG.class, args);
    }
}
