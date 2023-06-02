package com.nashtechglobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DBExamplerApplication {

    /**
     * This method is the entry point for the DBExampler application.
     * It calls the SpringApplication.run method to start the application.
     *
     * @param args an array of command-line arguments passed to the application
     */
    public static void main(final String[] args) {
        SpringApplication.run(DBExamplerApplication.class, args);

    }
}
