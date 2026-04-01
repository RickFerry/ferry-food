package com.ferry.food.infrastructure.config;

import com.ferry.food.domain.domainservices.ValidadorRestaurante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main infrastructure configuration class for Ferry Food application.
 *
 * This configuration centralizes Spring framework setup and bean wiring,
 * keeping infrastructure concerns separate from domain and application logic.
 *
 * Component Scanning Strategy:
 * - Domain Layer: com.ferry.food.domain (entities, value objects, domain services)
 * - Application Layer: com.ferry.food.application (use cases)
 * - Adapter Layer: com.ferry.food.adapter (HTTP controllers, persistence adapters)
 * - Infrastructure Layer: com.ferry.food.infrastructure (configuration, beans)
 *
 * Dependency Inversion:
 * - Domain layer: NO framework dependencies, pure business logic
 * - Application layer: Depends on domain ports; instantiates domain services
 * - Adapter layer: Depends on domain ports; implements infrastructure concerns
 * - Infrastructure layer: Configures Spring and wires all layers
 *
 * Key Architectural Patterns:
 * 1. All adapters implement domain ports (interfaces)
 * 2. Use cases are instantiated as @Component beans (automatically scanned)
 * 3. Repository adapters are instantiated as @Component beans (automatically scanned)
 * 4. HTTP controllers receive use case ports via constructor injection
 * 5. All configuration is centralized here, not scattered in adapters
 *
 * Component Types Auto-discovered:
 * - @Component: Used for use cases and repository adapters
 * - @RestController: Used for HTTP controllers
 * - @Repository: Used for Spring Data JPA repositories (infrastructure)
 *
 * Note: This configuration follows the hexagonal architecture pattern where the
 * infrastructure layer is responsible for bootstrapping the application, not
 * the adapters themselves.
 */
@Configuration
@ComponentScan({
    "com.ferry.food.domain.domainservices",
    "com.ferry.food.application.usecases",
    "com.ferry.food.adapter.input.http.controller",
    "com.ferry.food.adapter.input.http.advice",
    "com.ferry.food.adapter.output.persistence.adapter",
    "com.ferry.food.adapter.output.persistence.repository"
})
public class InfrastructureConfiguration {
    // ...existing code...

    /**
     * Provides ValidadorRestaurante bean.
     *
     * This bean is explicitly defined here rather than using @Component on the class
     * to keep the domain layer free of framework annotations (maintaining pure domain logic).
     *
     * The ValidadorRestaurante is a domain service that enforces business rules and is
     * instantiated in the infrastructure layer as part of the dependency inversion pattern.
     */
    @Bean
    public ValidadorRestaurante validadorRestaurante() {
        return new ValidadorRestaurante();
    }
}

