package com.progcon.programacionconcurrentefinal.spell_management.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ruta de ejemplo: todas las peticiones a /api/spells/** se redirigen al módulo Spell Management
                .route("spell_route", r -> r.path("/api/spells/**")
                        .uri("http://localhost:8080"))
                .build();
    }
}
