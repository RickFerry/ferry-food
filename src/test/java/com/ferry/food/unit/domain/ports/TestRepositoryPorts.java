package com.ferry.food.unit.domain.ports;

import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Repository Ports Tests")
class TestRepositoryPorts {

    @Test
    @DisplayName("should have CozinhaRepositoryPort interface defined")
    void testCozinhaRepositoryPort_Definida() {
        // Act & Assert
        assertThat(CozinhaRepositoryPort.class).isInterface();
        assertThat(CozinhaRepositoryPort.class.getDeclaredMethods()).hasSize(5);
    }

    @Test
    @DisplayName("should have CozinhaRepositoryPort with salvar method")
    void testCozinhaRepositoryPort_ContemMetodoSalvar() {
        // Act & Assert
        assertThat(CozinhaRepositoryPort.class.getDeclaredMethods())
            .extracting(m -> m.getName())
            .contains("salvar");
    }

    @Test
    @DisplayName("should have CozinhaRepositoryPort with obterPorId method")
    void testCozinhaRepositoryPort_ContemMetodoObterPorId() {
        // Act & Assert
        assertThat(CozinhaRepositoryPort.class.getDeclaredMethods())
            .extracting(m -> m.getName())
            .contains("obterPorId");
    }

    @Test
    @DisplayName("should have CozinhaRepositoryPort with listarTodas method")
    void testCozinhaRepositoryPort_ContemMetodoListarTodas() {
        // Act & Assert
        assertThat(CozinhaRepositoryPort.class.getDeclaredMethods())
            .extracting(m -> m.getName())
            .contains("listarTodas");
    }

    @Test
    @DisplayName("should have CozinhaRepositoryPort with deletar method")
    void testCozinhaRepositoryPort_ContemMetodoDeletar() {
        // Act & Assert
        assertThat(CozinhaRepositoryPort.class.getDeclaredMethods())
            .extracting(m -> m.getName())
            .contains("deletar");
    }

    @Test
    @DisplayName("should have CozinhaRepositoryPort with existePorId method")
    void testCozinhaRepositoryPort_ContemMetodoExistePorId() {
        // Act & Assert
        assertThat(CozinhaRepositoryPort.class.getDeclaredMethods())
            .extracting(m -> m.getName())
            .contains("existePorId");
    }

    @Test
    @DisplayName("should have RestauranteRepositoryPort interface defined")
    void testRestauranteRepositoryPort_Definida() {
        // Act & Assert
        assertThat(RestauranteRepositoryPort.class).isInterface();
        assertThat(RestauranteRepositoryPort.class.getDeclaredMethods().length).isGreaterThan(0);
    }

    @Test
    @DisplayName("should have RestauranteRepositoryPort with salvar method")
    void testRestauranteRepositoryPort_ContemMetodoSalvar() {
        // Act & Assert
        assertThat(RestauranteRepositoryPort.class.getDeclaredMethods())
            .extracting(m -> m.getName())
            .contains("salvar");
    }

    @Test
    @DisplayName("should have RestauranteRepositoryPort with obterPorId method")
    void testRestauranteRepositoryPort_ContemMetodoObterPorId() {
        // Act & Assert
        assertThat(RestauranteRepositoryPort.class.getDeclaredMethods())
            .extracting(m -> m.getName())
            .contains("obterPorId");
    }
}
