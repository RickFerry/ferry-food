package com.ferry.food.unit.domain.entities;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.entities.Estado;
import com.ferry.food.fixtures.CidadeFixture;
import com.ferry.food.fixtures.EstadoFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Cidade Entity Tests")
class TestCidade {

    @Test
    @DisplayName("should create Cidade with valid Estado")
    void testCriarCidade_ComEstadoValido() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();

        // Act
        Cidade cidade = CidadeFixture.umaCidadeValida(estado);

        // Assert
        assertThat(cidade).isNotNull();
        assertThat(cidade.getId()).isEqualTo(1L);
        assertThat(cidade.getNome().getValor()).isEqualTo("São Paulo");
        assertThat(cidade.getEstado()).isEqualTo(estado);
    }

    @Test
    @DisplayName("should update Cidade name")
    void testAtualizarNome_DeveMudarNome() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade = CidadeFixture.umaCidadeValida(estado);
        String novoNome = "Campinas";

        // Act
        cidade.atualizarNome(novoNome);

        // Assert
        assertThat(cidade.getNome().getValor()).isEqualTo(novoNome);
    }

    @Test
    @DisplayName("should update Cidade Estado")
    void testAtualizarEstado_DeveAtualizarEstado() {
        // Arrange
        Estado estado1 = EstadoFixture.umEstadoComIdENome(1L, "São Paulo");
        Estado estado2 = EstadoFixture.umEstadoComIdENome(2L, "Rio de Janeiro");
        Cidade cidade = CidadeFixture.umaCidadeValida(estado1);

        // Act
        cidade.atualizarEstado(estado2);

        // Assert
        assertThat(cidade.getEstado()).isEqualTo(estado2);
    }

    @Test
    @DisplayName("should throw exception when updating Cidade with null Estado")
    void testAtualizarEstado_ComEstadoNulo_DeveLançarException() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade = CidadeFixture.umaCidadeValida(estado);

        // Act & Assert
        assertThatThrownBy(() -> cidade.atualizarEstado(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Estado não pode ser nulo");
    }

    @Test
    @DisplayName("should be equal when comparing Cidades with same id")
    void testEquality_CidadesComMesmoId_DeveSerIguais() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade1 = Cidade.criar(1L, "São Paulo", estado);
        Cidade cidade2 = Cidade.criar(1L, "Campinas", estado);

        // Act & Assert
        assertThat(cidade1).isEqualTo(cidade2);
    }

    @Test
    @DisplayName("should not be equal when comparing Cidades with different id")
    void testEquality_CidadesComIdsDiferentes_DeveSerDiferentes() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade1 = Cidade.criar(1L, "São Paulo", estado);
        Cidade cidade2 = Cidade.criar(2L, "São Paulo", estado);

        // Act & Assert
        assertThat(cidade1).isNotEqualTo(cidade2);
    }

    @Test
    @DisplayName("should have same hashCode for Cidades with same id")
    void testHashCode_CidadesComMesmoId_DeveTerMesmoHashCode() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade1 = Cidade.criar(1L, "São Paulo", estado);
        Cidade cidade2 = Cidade.criar(1L, "Campinas", estado);

        // Act & Assert
        assertThat(cidade1).hasSameHashCodeAs(cidade2);
    }

    @Test
    @DisplayName("should access Cidade id")
    void testObterID_DeveRetornarId() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade = Cidade.criar(5L, "São Paulo", estado);

        // Act & Assert
        assertThat(cidade.getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("should access Cidade nome")
    void testObterNome_DeveRetornarNome() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade = Cidade.criar(1L, "São Paulo", estado);

        // Act & Assert
        assertThat(cidade.getNome().getValor()).isEqualTo("São Paulo");
    }

    @Test
    @DisplayName("should access Cidade Estado")
    void testObterEstado_DeveRetornarEstado() {
        // Arrange
        Estado estado = EstadoFixture.umEstadoValido();
        Cidade cidade = Cidade.criar(1L, "São Paulo", estado);

        // Act & Assert
        assertThat(cidade.getEstado()).isEqualTo(estado);
    }
}
