package com.github.emmanuelowusu.shortlink;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * The OpenApiConfig class provides configuration for OpenAPI documentation in the ShortLink application.
 *
 * @Configuration This annotation indicates that this class provides bean definitions for Spring configuration.
 *
 * @OpenAPIDefinition This annotation configures the generation of OpenAPI (Swagger) documentation for the application.
 *   - info: Defines information about the API documented.
 *       - title: The title of the API documentation.
 *       - version: The version of the API documented.
 *       - description: A brief description of the API functionalities.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ShortLink ✨\uD83D\uDD17 API Documentation",
                version = "1.0",
                description = "Explore the available endpoints for ShortLink — a simple URL-shortening service"
        )
)
public class OpenApiConfig { }

