package com.nashtechglobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Used to bootstrap and launch a Spring Boot Application from Java main method.
 */
@SpringBootApplication
public class ReactiveExamplerApplication {

    /**
     * A basic main that can be used to launch an application.
     * @param args  the application arguments.
     */
    public static void main(final String[] args) {
        SpringApplication.run(ReactiveExamplerApplication.class, args);
    }
}
