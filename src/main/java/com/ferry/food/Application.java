package com.ferry.food;

import com.ferry.food.infrastructure.config.InfrastructureConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main entry point for the Ferry Food application.
 *
 * Architecture: Hexagonal (Ports & Adapters)
 * - Domain layer is framework-agnostic
 * - Application layer orchestrates use cases
 * - Adapter layer handles HTTP input and persistence output
 * - Infrastructure layer (loaded via @Import) handles Spring configuration
 *
 * All Spring configuration is centralized in InfrastructureConfiguration
 * to maintain clear separation of concerns.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ferry.food.adapter.output.persistence.repository")
@Import(InfrastructureConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
