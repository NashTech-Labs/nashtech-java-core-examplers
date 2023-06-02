package com.mongodb.config;

import com.nashtechglobal.exception.handler.CustomExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Configuration
public class CustomExceptionConfig {

    /**
     * Return Controller advice bean.
     * @return CustomExceptionHandler
     */
    @Bean
    @ConditionalOnMissingBean(annotation = ControllerAdvice.class)
    public CustomExceptionHandler customExceptionHandler() {
        return new CustomExceptionHandler();
    }
}
