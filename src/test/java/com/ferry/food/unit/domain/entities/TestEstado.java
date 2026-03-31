package com.ferry.food.unit.domain.entities;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.fixtures.EstadoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Estado Entity Tests")
class TestEstado {

    @Test
    @DisplayName("should create Estado with valid data")
    void testCriarEstado_ComDadosValidos() {
        // Arrange & Act
        Estado estado = EstadoFixture.umEstadoValido();

        // Assert
        assertThat(estado).isNotNull();
        assertThat(estado.getId()).isEqualTo(1L);
        assertThat(estado.getNome().getValor()).isEqualTo("São Paulo");
    }

    @Test
    @DisplayName("should create new Estado without id")
    void testCriarNovoEstado_ComNomeValido() {
        // Arrange & Act
        Estado estado = EstadoFixture.umEstadoNovo();

        // Assert
        assertThat(estado).isNotNull();
        assertThat(estado.getId()).isNull();
        assertThat(estado.getNome().getValor()).isEqualTo("São Paulo");
    }

    @Test
    @DisplayName("should update Estado name")
    void testAtualizarNome_DeveMudarNome() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        String novoNome = "Rio de Janeiro";

        // Act
        estado.atualizarNome(novoNome);

        // Assert
        assertThat(estado.getNome().getValor()).isEqualTo(novoNome);
    }

    @Test
    @DisplayName("should throw exception when updating Estado name with invalid value")
    void testAtualizarNome_ComNomeInvalido_DeveLançarException() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();

        // Act & Assert
        assertThatThrownBy(() -> estado.atualizarNome(""))
            .hasMessageContaining("Nome não pode ser vazio");
    }

    @Test
    @DisplayName("should be equal when comparing Estados with same id")
    void testEquality_EstadosComMesmoId_DeveSerIguais() {
        // Arrange
        Estado estado1 = Estado.criar(1L, "São Paulo");
        Estado estado2 = Estado.criar(1L, "Rio de Janeiro");

        // Act & Assert
        assertThat(estado1).isEqualTo(estado2);
    }

    @Test
    @DisplayName("should not be equal when comparing Estados with different id")
    void testEquality_EstadosComIdsDiferentes_DeveSerDiferentes() {
        // Arrange
        Estado estado1 = Estado.criar(1L, "São Paulo");
        Estado estado2 = Estado.criar(2L, "São Paulo");

        // Act & Assert
        assertThat(estado1).isNotEqualTo(estado2);
    }

    @Test
    @DisplayName("should have same hashCode for Estados with same id")
    void testHashCode_EstadosComMesmoId_DeveTerMesmoHashCode() {
        // Arrange
        Estado estado1 = Estado.criar(1L, "São Paulo");
        Estado estado2 = Estado.criar(1L, "Rio de Janeiro");

        // Act & Assert
        assertThat(estado1).hasSameHashCodeAs(estado2);
    }

    @Test
    @DisplayName("should access Estado id")
    void testObterID_DeveRetornarId() {
        // Arrange
        Estado estado = Estado.criar(5L, "São Paulo");

        // Act & Assert
        assertThat(estado.getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("should access Estado nome")
    void testObterNome_DeveRetornarNome() {
        // Arrange
        Estado estado = Estado.criar(1L, "São Paulo");

        // Act & Assert
        assertThat(estado.getNome().getValor()).isEqualTo("São Paulo");
    }
}
