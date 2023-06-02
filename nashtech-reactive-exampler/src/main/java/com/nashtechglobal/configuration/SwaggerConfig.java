package com.nashtechglobal.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =
@Info(
        title = "Test the Reactive Methods.",
        version = "1.0-SNAPSHOT",
        termsOfService = "Reactive Methods",
        license = @License(url = "www.nashtechglobal.com",
                name = "NashTechGlobal"),
        contact = @Contact(name = "Nashtech Global",
                email = "Nashtech")
))

public class SwaggerConfig {

    /**
     * Return OpenAPI bean.
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(new io.swagger
                .v3.oas.models.info.Info()
                .title("Reactive Module")
                .version("1.0-SNAPSHOT")
                .description("Reactive module doc"));
    }
}
