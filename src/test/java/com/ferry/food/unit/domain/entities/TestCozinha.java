package com.ferry.food.unit.domain.entities;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.fixtures.CozinhaFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Cozinha Entity Tests")
class TestCozinha {

    @Test
    @DisplayName("should create Cozinha with valid data")
    void testCriarCozinha_ComDadosValidos() {
        // Arrange & Act
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();

        // Assert
        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isEqualTo(1L);
        assertThat(cozinha.getNome().getValor()).isEqualTo("Italiana");
    }

    @Test
    @DisplayName("should create new Cozinha without id")
    void testCriarNovaCozinha_ComNomeValido() {
        // Arrange & Act
        Cozinha cozinha = CozinhaFixture.umaCozinhaNova();

        // Assert
        assertThat(cozinha).isNotNull();
        assertThat(cozinha.getId()).isNull();
        assertThat(cozinha.getNome().getValor()).isEqualTo("Italiana");
    }

    @Test
    @DisplayName("should update Cozinha name")
    void testAtualizarNome_DeveMudarNome() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        String novoNome = "Chinesa";

        // Act
        cozinha.atualizarNome(novoNome);

        // Assert
        assertThat(cozinha.getNome().getValor()).isEqualTo(novoNome);
    }

    @Test
    @DisplayName("should throw exception when updating Cozinha name with invalid value")
    void testAtualizarNome_ComNomeInvalido_DeveLançarException() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();

        // Act & Assert
        assertThatThrownBy(() -> cozinha.atualizarNome(""))
            .hasMessageContaining("Nome não pode ser vazio");
    }

    @Test
    @DisplayName("should be equal when comparing Cozinhas with same id")
    void testEquality_CozinhasComMesmoId_DeveSerIguais() {
        // Arrange
        Cozinha cozinha1 = Cozinha.criar(1L, "Italiana");
        Cozinha cozinha2 = Cozinha.criar(1L, "Chinesa");

        // Act & Assert
        assertThat(cozinha1).isEqualTo(cozinha2);
    }

    @Test
    @DisplayName("should not be equal when comparing Cozinhas with different id")
    void testEquality_CozinhasComIdsDiferentes_DeveSerDiferentes() {
        // Arrange
        Cozinha cozinha1 = Cozinha.criar(1L, "Italiana");
        Cozinha cozinha2 = Cozinha.criar(2L, "Italiana");

        // Act & Assert
        assertThat(cozinha1).isNotEqualTo(cozinha2);
    }

    @Test
    @DisplayName("should have same hashCode for Cozinhas with same id")
    void testHashCode_CozinhasComMesmoId_DeveTerMesmoHashCode() {
        // Arrange
        Cozinha cozinha1 = Cozinha.criar(1L, "Italiana");
        Cozinha cozinha2 = Cozinha.criar(1L, "Chinesa");

        // Act & Assert
        assertThat(cozinha1).hasSameHashCodeAs(cozinha2);
    }

    @Test
    @DisplayName("should access Cozinha id")
    void testObterID_DeveRetornarId() {
        // Arrange
        Cozinha cozinha = Cozinha.criar(5L, "Italiana");

        // Act & Assert
        assertThat(cozinha.getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("should access Cozinha nome")
    void testObterNome_DeveRetornarNome() {
        // Arrange
        Cozinha cozinha = Cozinha.criar(1L, "Italiana");

        // Act & Assert
        assertThat(cozinha.getNome().getValor()).isEqualTo("Italiana");
    }
}
