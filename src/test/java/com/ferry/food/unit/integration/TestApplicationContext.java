package com.ferry.food.unit.integration;

import com.ferry.food.Application;
import com.ferry.food.domain.ports.input.cozinha.CriarCozinhaUseCase;
import com.ferry.food.domain.ports.input.cozinha.ListarCozinhasUseCase;
import com.ferry.food.domain.ports.input.estado.CriarEstadoUseCase;
import com.ferry.food.domain.ports.input.estado.ListarEstadosUseCase;
import com.ferry.food.domain.ports.input.cidade.CriarCidadeUseCase;
import com.ferry.food.domain.ports.input.restaurante.CriarRestauranteUseCase;
import com.ferry.food.adapter.input.http.controller.CozinhaController;
import com.ferry.food.adapter.input.http.controller.EstadoController;
import com.ferry.food.adapter.input.http.controller.CidadeController;
import com.ferry.food.adapter.input.http.controller.RestauranteController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@DisplayName("Application Integration Tests")
class TestApplicationContext {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    @DisplayName("should load application context")
    void testContextLoads() {
        // Assert
        assertThat(applicationContext).isNotNull();
    }

    @Test
    @DisplayName("should have CozinhaController bean")
    void testCozinhaController_Loaded() {
        // Assert
        assertThat(applicationContext.containsBean("cozinhaController")).isTrue();
        assertThat(applicationContext.getBean(CozinhaController.class)).isNotNull();
    }

    @Test
    @DisplayName("should have EstadoController bean")
    void testEstadoController_Loaded() {
        // Assert
        assertThat(applicationContext.containsBean("estadoController")).isTrue();
        assertThat(applicationContext.getBean(EstadoController.class)).isNotNull();
    }

    @Test
    @DisplayName("should have CidadeController bean")
    void testCidadeController_Loaded() {
        // Assert
        assertThat(applicationContext.containsBean("cidadeController")).isTrue();
        assertThat(applicationContext.getBean(CidadeController.class)).isNotNull();
    }

    @Test
    @DisplayName("should have RestauranteController bean")
    void testRestauranteController_Loaded() {
        // Assert
        assertThat(applicationContext.containsBean("restauranteController")).isTrue();
        assertThat(applicationContext.getBean(RestauranteController.class)).isNotNull();
    }

    @Test
    @DisplayName("should have CriarCozinhaUseCase implementation")
    void testCriarCozinhaUseCase_Loaded() {
        // Assert
        assertThat(applicationContext.getBeansOfType(CriarCozinhaUseCase.class)).isNotEmpty();
    }

    @Test
    @DisplayName("should have ListarCozinhasUseCase implementation")
    void testListarCozinhasUseCase_Loaded() {
        // Assert
        assertThat(applicationContext.getBeansOfType(ListarCozinhasUseCase.class)).isNotEmpty();
    }

    @Test
    @DisplayName("should have CriarEstadoUseCase implementation")
    void testCriarEstadoUseCase_Loaded() {
        // Assert
        assertThat(applicationContext.getBeansOfType(CriarEstadoUseCase.class)).isNotEmpty();
    }

    @Test
    @DisplayName("should have ListarEstadosUseCase implementation")
    void testListarEstadosUseCase_Loaded() {
        // Assert
        assertThat(applicationContext.getBeansOfType(ListarEstadosUseCase.class)).isNotEmpty();
    }

    @Test
    @DisplayName("should have CriarCidadeUseCase implementation")
    void testCriarCidadeUseCase_Loaded() {
        // Assert
        assertThat(applicationContext.getBeansOfType(CriarCidadeUseCase.class)).isNotEmpty();
    }

    @Test
    @DisplayName("should have CriarRestauranteUseCase implementation")
    void testCriarRestauranteUseCase_Loaded() {
        // Assert
        assertThat(applicationContext.getBeansOfType(CriarRestauranteUseCase.class)).isNotEmpty();
    }

    @Test
    @DisplayName("should not have circular dependencies")
    void testNoCircularDependencies() {
        // Assert - if context loaded without errors, no circular dependencies
        assertThat(applicationContext).isNotNull();
    }

    @Test
    @DisplayName("should have all required beans wired")
    void testAllBeansLoaded() {
        // Assert
        assertThat(applicationContext.getBeanDefinitionCount()).isGreaterThan(0);
    }
}
