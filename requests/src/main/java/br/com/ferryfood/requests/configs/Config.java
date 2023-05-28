package br.com.ferryfood.requests.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class Config {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
