package com.ferry.food.unit.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;
import com.ferry.food.domain.valueobjects.Nome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nome Value Object Tests")
class TestNome {

    @Test
    @DisplayName("should create valid Nome with valid string")
    void testNomeValido_DeveSerCriado() {
        // Arrange
        String nomeValido = "Italiana";

        // Act
        Nome nome = new Nome(nomeValido);

        // Assert
        assertThat(nome).isNotNull();
        assertThat(nome.getValor()).isEqualTo(nomeValido);
    }

    @Test
    @DisplayName("should throw ValidationException when Nome is empty")
    void testNomeVazio_DeveLançarException() {
        // Arrange
        String nomeVazio = "";

        // Act & Assert
        assertThatThrownBy(() -> new Nome(nomeVazio))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Nome não pode ser vazio");
    }

    @Test
    @DisplayName("should throw ValidationException when Nome is null")
    void testNomeNulo_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Nome(null))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Nome não pode ser vazio");
    }

    @Test
    @DisplayName("should throw ValidationException when Nome has less than 3 characters")
    void testNomeComMenosDeTresCaracteres_DeveLançarException() {
        // Arrange
        String nomeInvalido = "AB";

        // Act & Assert
        assertThatThrownBy(() -> new Nome(nomeInvalido))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Nome deve ter no mínimo 3 caracteres");
    }

    @Test
    @DisplayName("should throw ValidationException when Nome exceeds 255 characters")
    void testNomeComMaisDe255Caracteres_DeveLançarException() {
        // Arrange
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            sb.append("A");
        }
        String nomeLongo = sb.toString();

        // Act & Assert
        assertThatThrownBy(() -> new Nome(nomeLongo))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Nome não pode exceder 255 caracteres");
    }

    @Test
    @DisplayName("should validate Nome with single word")
    void testNomeComPalavraUnica_DeveValidar() {
        // Arrange
        String nomePalavraUnica = "Italiana";

        // Act
        Nome nome = new Nome(nomePalavraUnica);

        // Assert
        assertThat(nome.getValor()).isEqualTo(nomePalavraUnica);
    }

    @Test
    @DisplayName("should validate Nome with multiple words")
    void testNomeComMultiplasPalavras_DeveValidar() {
        // Arrange
        String nomePalavras = "Pizzaria Italiana Tradicional";

        // Act
        Nome nome = new Nome(nomePalavras);

        // Assert
        assertThat(nome.getValor()).isEqualTo(nomePalavras);
    }

    @Test
    @DisplayName("should contain word ignoring case")
    void testContemPalavra_DeveIgnorarCase() {
        // Arrange
        Nome nome = new Nome("Pizzaria Italiana");

        // Act & Assert
        assertThat(nome.contemPalavra("italiana")).isTrue();
        assertThat(nome.contemPalavra("ITALIANA")).isTrue();
        assertThat(nome.contemPalavra("pizzaria")).isTrue();
    }

    @Test
    @DisplayName("should return false when searching for non-existent word")
    void testContemPalavra_PalavraInexistente_DeveRetornarFalse() {
        // Arrange
        Nome nome = new Nome("Pizzaria Italiana");

        // Act & Assert
        assertThat(nome.contemPalavra("Chinesa")).isFalse();
    }

    @Test
    @DisplayName("should return formatted string for display")
    void testParaExibicao_DeveTrimmar() {
        // Arrange
        Nome nome = new Nome("  Italiana  ");

        // Act
        String resultado = nome.paraExibicao();

        // Assert
        assertThat(resultado).isEqualTo("Italiana");
    }

    @Test
    @DisplayName("should be equal when comparing identical Nome values")
    void testEquality_NomesComMesmoValor_DeveSerIguais() {
        // Arrange
        Nome nome1 = new Nome("Italiana");
        Nome nome2 = new Nome("Italiana");

        // Act & Assert
        assertThat(nome1).isEqualTo(nome2);
    }

    @Test
    @DisplayName("should not be equal when comparing different Nome values")
    void testEquality_NomesComValoresDiferentes_DeveSerDiferentes() {
        // Arrange
        Nome nome1 = new Nome("Italiana");
        Nome nome2 = new Nome("Chinesa");

        // Act & Assert
        assertThat(nome1).isNotEqualTo(nome2);
    }
}
