package com.ly.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return route("product_service").GET("/api/products",http())
                .before(uri("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return route("order_service")
                .POST("/api/orders",http())
                .before(uri("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return route("inventory_service")
                .GET("/api/inventory",http())
                .before(uri("http://localhost:8082"))
                .build();
    }
}
