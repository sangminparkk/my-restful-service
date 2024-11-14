package com.chandler.myrestfulservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
                title = "Restful API 명세서",
                description = "Spring Boot 기반 API 개발",
                version = "v1.0.0")
)
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/users/**", "/admin/**"};

        return GroupedOpenApi.builder()
                .group("일반 사용자와 관리자를 위한 User 도메인 API")
                .pathsToMatch(paths)
                .build();
    }

}
