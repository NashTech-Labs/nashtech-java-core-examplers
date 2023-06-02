package com.nashtech.observation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nashtechglobal")
public class ObservationExamplerApplication {

    /**
     * Application bootstrap.
     * @param args
     */
    public static void main(final String[] args) {
        SpringApplication.run(ObservationExamplerApplication.class, args);
    }
}
