package com.ferry.food.unit.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;
import com.ferry.food.domain.valueobjects.Taxa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Taxa Value Object Tests")
class TestTaxa {

    @Test
    @DisplayName("should create valid Taxa with positive value")
    void testTaxaPositiva_DeveSerCriada() {
        // Arrange
        BigDecimal valor = new BigDecimal("15.50");

        // Act
        Taxa taxa = new Taxa(valor);

        // Assert
        assertThat(taxa).isNotNull();
        assertThat(taxa.getValor()).isEqualTo(valor);
    }

    @Test
    @DisplayName("should create valid Taxa with zero value")
    void testTaxaZero_DeveSerCriada() {
        // Arrange
        BigDecimal valor = BigDecimal.ZERO;

        // Act
        Taxa taxa = new Taxa(valor);

        // Assert
        assertThat(taxa).isNotNull();
        assertThat(taxa.getValor()).isEqualTo(valor);
    }

    @Test
    @DisplayName("should throw ValidationException when Taxa is null")
    void testTaxaNula_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Taxa(null))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Valor da taxa não pode ser nulo");
    }

    @Test
    @DisplayName("should throw ValidationException when Taxa is negative")
    void testTaxaNegativa_DeveLançarException() {
        // Arrange
        BigDecimal valorNegativo = new BigDecimal("-10.00");

        // Act & Assert
        assertThatThrownBy(() -> new Taxa(valorNegativo))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Valor da taxa não pode ser negativo");
    }

    @Test
    @DisplayName("should sum two taxas correctly")
    void testSomar_DeveRetornarSomaCorreta() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("10.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("5.50"));

        // Act
        Taxa resultado = taxa1.somar(taxa2);

        // Assert
        assertThat(resultado.getValor()).isEqualTo(new BigDecimal("15.50"));
    }

    @Test
    @DisplayName("should return same taxa when adding null")
    void testSomar_ComTaxaNula_DeveRetornarMesmaTaxa() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("10.00"));

        // Act
        Taxa resultado = taxa1.somar(null);

        // Assert
        assertThat(resultado.getValor()).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    @DisplayName("should subtract two taxas correctly")
    void testSubtrair_DeveRetornarDiferencaCorreta() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("15.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("5.50"));

        // Act
        Taxa resultado = taxa1.subtrair(taxa2);

        // Assert
        assertThat(resultado.getValor()).isEqualTo(new BigDecimal("9.50"));
    }

    @Test
    @DisplayName("should throw ValidationException when subtraction result is negative")
    void testSubtrair_ResultadoNegativo_DeveLançarException() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("5.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThatThrownBy(() -> taxa1.subtrair(taxa2))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Resultado da subtração não pode ser negativo");
    }

    @Test
    @DisplayName("should return same taxa when subtracting null")
    void testSubtrair_ComTaxaNula_DeveRetornarMesmaTaxa() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("10.00"));

        // Act
        Taxa resultado = taxa1.subtrair(null);

        // Assert
        assertThat(resultado.getValor()).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    @DisplayName("should multiply taxa by multiplier correctly")
    void testMultiplicar_DeveRetornarProdutoCorreto() {
        // Arrange
        Taxa taxa = new Taxa(new BigDecimal("10.00"));
        BigDecimal multiplicador = new BigDecimal("2.5");

        // Act
        Taxa resultado = taxa.multiplicar(multiplicador);

        // Assert
        assertThat(resultado.getValor()).isEqualTo(new BigDecimal("25.00"));
    }

    @Test
    @DisplayName("should throw ValidationException when multiplier is null")
    void testMultiplicar_ComMultiplicadorNulo_DeveLançarException() {
        // Arrange
        Taxa taxa = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThatThrownBy(() -> taxa.multiplicar(null))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Multiplicador não pode ser nulo");
    }

    @Test
    @DisplayName("should return true when taxa is greater than or equal to another")
    void testEhMaiorOuIgualA_DeveRetornarTrue() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("15.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThat(taxa1.ehMaiorOuIgualA(taxa2)).isTrue();
    }

    @Test
    @DisplayName("should return false when taxa is less than another")
    void testEhMaiorOuIgualA_DeveRetornarFalse() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("5.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThat(taxa1.ehMaiorOuIgualA(taxa2)).isFalse();
    }

    @Test
    @DisplayName("should return true when taxa is less than or equal to another")
    void testEhMenorOuIgualA_DeveRetornarTrue() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("5.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThat(taxa1.ehMenorOuIgualA(taxa2)).isTrue();
    }

    @Test
    @DisplayName("should return false when taxa is greater than another")
    void testEhMenorOuIgualA_DeveRetornarFalse() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("15.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThat(taxa1.ehMenorOuIgualA(taxa2)).isFalse();
    }

    @Test
    @DisplayName("should return true when taxas are equal")
    void testEhIgualA_DeveRetornarTrue() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("10.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThat(taxa1.ehIgualA(taxa2)).isTrue();
    }

    @Test
    @DisplayName("should return false when taxas are not equal")
    void testEhIgualA_DeveRetornarFalse() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("10.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("15.00"));

        // Act & Assert
        assertThat(taxa1.ehIgualA(taxa2)).isFalse();
    }

    @Test
    @DisplayName("should be equal when comparing identical Taxa values")
    void testEquality_TaxasComMesmoValor_DeveSerIguais() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("10.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("10.00"));

        // Act & Assert
        assertThat(taxa1).isEqualTo(taxa2);
    }

    @Test
    @DisplayName("should not be equal when comparing different Taxa values")
    void testEquality_TaxasComValoresDiferentes_DeveSerDiferentes() {
        // Arrange
        Taxa taxa1 = new Taxa(new BigDecimal("10.00"));
        Taxa taxa2 = new Taxa(new BigDecimal("15.00"));

        // Act & Assert
        assertThat(taxa1).isNotEqualTo(taxa2);
    }
}
