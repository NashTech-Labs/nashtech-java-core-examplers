package com.nashtechglobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;

/**
 * Used to bootstrap and launch a Spring Boot Application from Java main method.
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,
        MongoReactiveAutoConfiguration.class})
public class ReactiveMongoApplication {
    /**
    * The main method to run the ReactiveMongoApplication class.
    * @param args An array of command-line arguments for the application.
    */
    public static void main(final String[] args) {
        SpringApplication.run(ReactiveMongoApplication.class, args);
    }
}
