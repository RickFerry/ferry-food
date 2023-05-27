package br.com.ferryfood.requests.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
