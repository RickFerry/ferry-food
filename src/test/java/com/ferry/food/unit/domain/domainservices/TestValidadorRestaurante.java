package com.ferry.food.unit.domain.domainservices;

import com.ferry.food.domain.domainservices.ValidadorRestaurante;
import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.exceptions.BusinessException;
import com.ferry.food.fixtures.CozinhaFixture;
import com.ferry.food.fixtures.RestauranteFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("ValidadorRestaurante Domain Service Tests")
class TestValidadorRestaurante {

    private ValidadorRestaurante validador = new ValidadorRestaurante();

    @Test
    @DisplayName("should validate creation with valid data")
    void testValidarParaCriacao_ComDadosValidos() {
        // Arrange
        String nome = "Pizzaria Italia";
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();

        // Act & Assert - should not throw
        assertThatCode(() -> validador.validarParaCriacao(nome, cozinha))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("should throw BusinessException when nome is empty")
    void testValidarParaCriacao_ComNomeVazio_DeveLançarException() {
        // Arrange
        String nomeVazio = "";
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();

        // Act & Assert
        assertThatThrownBy(() -> validador.validarParaCriacao(nomeVazio, cozinha))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Nome do restaurante é obrigatório");
    }

    @Test
    @DisplayName("should throw BusinessException when nome is null")
    void testValidarParaCriacao_ComNomeNulo_DeveLançarException() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();

        // Act & Assert
        assertThatThrownBy(() -> validador.validarParaCriacao(null, cozinha))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Nome do restaurante é obrigatório");
    }

    @Test
    @DisplayName("should throw BusinessException when Cozinha is null")
    void testValidarParaCriacao_ComCozinhaNula_DeveLançarException() {
        // Arrange
        String nome = "Pizzaria Italia";

        // Act & Assert
        assertThatThrownBy(() -> validador.validarParaCriacao(nome, null))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Cozinha do restaurante é obrigatória");
    }

    @Test
    @DisplayName("should throw BusinessException when Restaurante is null for update")
    void testValidarParaAtualizacao_ComRestauranteNulo_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> validador.validarParaAtualizacao(null))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Restaurante não pode ser nulo");
    }

    @Test
    @DisplayName("should throw BusinessException when Restaurante is null for deletion")
    void testValidarParaDelecao_ComRestauranteNulo_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> validador.validarParaDelecao(null))
            .isInstanceOf(BusinessException.class)
            .hasMessageContaining("Restaurante não pode ser nulo para deleção");
    }

    @Test
    @DisplayName("should validate deletion with valid Restaurante")
    void testValidarParaDelecao_ComRestauranteValido() {
        // Arrange
        Restaurante restaurante = RestauranteFixture.umRestauranteValido(CozinhaFixture.umaCozinhaValida());

        // Act & Assert - should not throw
        assertThatCode(() -> validador.validarParaDelecao(restaurante))
            .doesNotThrowAnyException();
    }
}
