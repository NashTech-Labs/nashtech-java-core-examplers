package com.nashtechglobal.config;

import com.nashtechglobal.exception.handler.CustomExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
public class CustomExceptionConfig {

    /**
     * Creates a bean for the controller advice.
     * @return CustomExceptionHandler
     */
    @Bean
    @ConditionalOnMissingBean(annotation = ControllerAdvice.class)
    public CustomExceptionHandler customExceptionHandler() {

        return new CustomExceptionHandler();
    }
}
