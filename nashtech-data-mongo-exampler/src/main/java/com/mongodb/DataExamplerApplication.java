package com.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.mongodb"})
public class DataExamplerApplication {
    /**
     * The main method to run the DataExamplerApplication class.
     * @param args An array of command-line arguments for the application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(DataExamplerApplication.class, args);

    }
}
