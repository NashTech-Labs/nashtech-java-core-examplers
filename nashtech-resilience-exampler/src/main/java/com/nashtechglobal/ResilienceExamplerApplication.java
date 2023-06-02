package com.nashtechglobal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is  the main class of resilience service.
 */
@SpringBootApplication(scanBasePackages = {"com.nashtechglobal"})
 public class ResilienceExamplerApplication {

    /**
     * The main method is the entry point for the application.
     * @param args the command-line arguments passed
     * to the application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(ResilienceExamplerApplication.class, args);
    }
}
