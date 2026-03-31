package com.ferry.food.adapter.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for all adapter beans.
 * 
 * Note: UseCase implementations are marked with @Component annotations in the application layer
 * and are automatically detected and registered by Spring's component scanning.
 * This allows for cleaner, more maintainable code compared to explicit @Bean definitions.
 * 
 * This class is kept as a placeholder for future configuration needs and serves as
 * documentation for the adapter architecture.
 */
@Configuration
public class AdapterConfiguration {
    // UseCase beans are automatically scanned from @Component annotations
}
