package com.sipriano.libraryapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Library API",
                version = "V1",
                contact = @Contact(
                        name = "Anderson Sipriano",
                        email = "Anderson.Sipriano@Libraryapi.com",
                        url = "Libraryapi.com"
                )
        )
)
public class OpenApiConfiguration {
}
