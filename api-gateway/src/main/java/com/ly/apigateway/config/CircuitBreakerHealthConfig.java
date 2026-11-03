package com.ly.apigateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.boot.health.contributor.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CircuitBreakerHealthConfig {

    @Bean
    public HealthIndicator circuitBreakerHealthIndicator(CircuitBreakerRegistry circuitBreakerRegistry) {
        return () ->{
            Health.Builder builder = Health.status(Status.UP);
            circuitBreakerRegistry.getAllCircuitBreakers().forEach(circuitBreaker -> {
                builder.withDetail(circuitBreaker.getName(), Map.of(
                        "state", circuitBreaker.getState(),
                        "failureRate", circuitBreaker.getMetrics().getFailureRate()+"%",
                        "bufferedCalls", circuitBreaker.getMetrics().getNumberOfBufferedCalls(),
                        "failedCalls", circuitBreaker.getMetrics().getNumberOfFailedCalls()
                        )
                );
            });
            return builder.build();
        };
    }
}
