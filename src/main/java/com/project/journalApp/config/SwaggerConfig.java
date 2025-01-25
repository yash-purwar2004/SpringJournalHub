package com.project.journalApp.config;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI().info(
            new Info().title("Journal App API")
            .description("SpringJournalHub is a modern, secure, and efficient solution for journal management, tailored for users who demand high performance and real-time capabilities.")
        )
        .servers(List.of(new Server().url("http://localhost:8080").description("Local Server"),
        new Server().url("http://localhost:8081").description("Production Server")))
        .tags(Arrays.asList(
            new Tag().name("Public APIs").description("Sign up and login"),
            new Tag().name("User APIs").description("Read, Update, and Delete user details"),
            new Tag().name("Journal Entry APIs").description("Create, Read, Update, and Delete journal entries"),
            new Tag().name("Admin APIs").description("Create and Read all users")
        )).addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new Components().addSecuritySchemes(
            "bearerAuth", new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name("Authorization")
        ));
    }
}
