package com.github.emmanuelowusu.shortlink;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "ShortLink API",
                version = "1.0",
                description = "Explore the available endpoints for ShortLink — a simple URL-shortening service"
        )
)
public class OpenApiConfig { }

