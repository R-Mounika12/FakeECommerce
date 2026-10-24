package com.example.FakeECommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fakeECommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FakeECommerce API")
                        .description("API documentation for FakeECommerce services")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("FakeECommerce Team")));
    }
}
