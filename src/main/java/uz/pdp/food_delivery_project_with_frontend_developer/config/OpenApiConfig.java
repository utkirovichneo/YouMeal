package uz.pdp.food_delivery_project_with_frontend_developer.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PDP Online Java")
                        .version("1")
                        .contact(new Contact()
                                .name("Ruzimbayev To'lqin")
                                .email("ruzimbayevtolqin@gmail.com")
                                .url("https://github.com/utkirovichneo")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server")
                ))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth").addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic"))
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Initial configuration")
                .pathsToMatch("/api/**")
                .build();
    }
}
