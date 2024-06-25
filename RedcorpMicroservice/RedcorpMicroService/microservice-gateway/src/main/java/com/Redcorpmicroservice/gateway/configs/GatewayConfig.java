package com.Redcorpmicroservice.gateway.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("employees", r -> r.path("/api/redcorp/v1/employee/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://172.31.11.92:8090"))
                .route("auth", r -> r.path("/api/redcorp/v1/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://172.31.11.92:8090"))
                .route("sections", r -> r.path("/api/redcorp/v1/section/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://172.31.11.92:9090"))
                .route("sections", r -> r.path("/api/redcorp/v1/project/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://172.31.11.92:9090"))
                .route("sections", r -> r.path("/api/redcorp/v1/team/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://172.31.11.92:9090"))
                .route("employees", r -> r.path("/api/redcorp/v1/task/**")
                        .filters(f -> f.filter(filter))
                        .uri("http://172.31.11.92:8070"))
                .build();
    }
}
