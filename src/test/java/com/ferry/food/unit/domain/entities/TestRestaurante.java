package com.ferry.food.unit.domain.entities;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.fixtures.CozinhaFixture;
import com.ferry.food.fixtures.RestauranteFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Restaurante Entity Tests")
class TestRestaurante {

    @Test
    @DisplayName("should create Restaurante with valid data")
    void testCriarRestaurante_ComDadosValidos() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();

        // Act
        Restaurante restaurante = RestauranteFixture.umRestauranteValido(cozinha);

        // Assert
        assertThat(restaurante).isNotNull();
        assertThat(restaurante.getId()).isEqualTo(1L);
        assertThat(restaurante.getNome().getValor()).isEqualTo("Pizzaria Italia");
        assertThat(restaurante.getCozinha()).isEqualTo(cozinha);
        assertThat(restaurante.getTaxaFrete().getValor()).isEqualTo(new BigDecimal("15.50"));
    }

    @Test
    @DisplayName("should update Restaurante data")
    void testAtualizarDados_DeveAtualizarRestaurante() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante = RestauranteFixture.umRestauranteValido(cozinha);

        // Act
        restaurante.atualizarDados(
            "Pizzaria Nova",
            new BigDecimal("20.00"),
            "Rua Nova",
            "456",
            "Complemento",
            "Bairro",
            "12345-678",
            1L
        );

        // Assert
        assertThat(restaurante.getNome().getValor()).isEqualTo("Pizzaria Nova");
        assertThat(restaurante.getTaxaFrete().getValor()).isEqualTo(new BigDecimal("20.00"));
        assertThat(restaurante.getEndereco().getLogradouro()).isEqualTo("Rua Nova");
    }

    @Test
    @DisplayName("should update Restaurante Cozinha")
    void testAtualizarCozinha_DeveAtualizarCozinha() {
        // Arrange
        Cozinha cozinha1 = CozinhaFixture.umaCozinhaComIdENome(1L, "Italiana");
        Cozinha cozinha2 = CozinhaFixture.umaCozinhaComIdENome(2L, "Chinesa");
        Restaurante restaurante = RestauranteFixture.umRestauranteValido(cozinha1);

        // Act
        restaurante.atualizarCozinha(cozinha2);

        // Assert
        assertThat(restaurante.getCozinha()).isEqualTo(cozinha2);
    }

    @Test
    @DisplayName("should throw exception when updating Restaurante with null Cozinha")
    void testAtualizarCozinha_ComCozinhaNula_DeveLançarException() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante = RestauranteFixture.umRestauranteValido(cozinha);

        // Act & Assert
        assertThatThrownBy(() -> restaurante.atualizarCozinha(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Cozinha não pode ser nula");
    }

    @Test
    @DisplayName("should check if delivery is free")
    void testTemFreteGratis_ComTaxaZero_DeveRetornarTrue() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante = RestauranteFixture.umRestauranteComTaxaFrete(BigDecimal.ZERO, cozinha);

        // Act & Assert
        assertThat(restaurante.temFreteGratis()).isTrue();
    }

    @Test
    @DisplayName("should return false when delivery is not free")
    void testTemFreteGratis_ComTaxaMaiorQueZero_DeveRetornarFalse() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante = RestauranteFixture.umRestauranteComTaxaFrete(new BigDecimal("15.50"), cozinha);

        // Act & Assert
        assertThat(restaurante.temFreteGratis()).isFalse();
    }

    @Test
    @DisplayName("should be equal when comparing Restaurantes with same id")
    void testEquality_RestaurantesComMesmoId_DeveSerIguais() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante1 = Restaurante.criar(
            1L, "Pizzaria Italia", new BigDecimal("15.50"),
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100",
            1L, cozinha, java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
        );
        Restaurante restaurante2 = Restaurante.criar(
            1L, "Pizzaria Nova", new BigDecimal("20.00"),
            "Rua Nova", "456", "Complemento", "Bairro", "12345-678",
            1L, cozinha, java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
        );

        // Act & Assert
        assertThat(restaurante1).isEqualTo(restaurante2);
    }

    @Test
    @DisplayName("should not be equal when comparing Restaurantes with different id")
    void testEquality_RestaurantesComIdsDiferentes_DeveSerDiferentes() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante1 = Restaurante.criar(
            1L, "Pizzaria Italia", new BigDecimal("15.50"),
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100",
            1L, cozinha, java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
        );
        Restaurante restaurante2 = Restaurante.criar(
            2L, "Pizzaria Italia", new BigDecimal("15.50"),
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100",
            1L, cozinha, java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
        );

        // Act & Assert
        assertThat(restaurante1).isNotEqualTo(restaurante2);
    }

    @Test
    @DisplayName("should have same hashCode for Restaurantes with same id")
    void testHashCode_RestaurantesComMesmoId_DeveTerMesmoHashCode() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante1 = Restaurante.criar(
            1L, "Pizzaria Italia", new BigDecimal("15.50"),
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100",
            1L, cozinha, java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
        );
        Restaurante restaurante2 = Restaurante.criar(
            1L, "Pizzaria Nova", new BigDecimal("20.00"),
            "Rua Nova", "456", "Complemento", "Bairro", "12345-678",
            1L, cozinha, java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
        );

        // Act & Assert
        assertThat(restaurante1).hasSameHashCodeAs(restaurante2);
    }

    @Test
    @DisplayName("should access Restaurante id")
    void testObterID_DeveRetornarId() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();
        Restaurante restaurante = Restaurante.criar(
            5L, "Pizzaria Italia", new BigDecimal("15.50"),
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100",
            1L, cozinha, java.time.LocalDateTime.now(), java.time.LocalDateTime.now()
        );

        // Act & Assert
        assertThat(restaurante.getId()).isEqualTo(5L);
    }

    @Test
    @DisplayName("should have initialized formasPagamento collection")
    void testFormasPagamento_DeveEstarInicializado() {
        // Arrange
        Cozinha cozinha = CozinhaFixture.umaCozinhaValida();

        // Act
        Restaurante restaurante = RestauranteFixture.umRestauranteValido(cozinha);

        // Assert
        assertThat(restaurante.getFormasPagamento()).isNotNull();
        assertThat(restaurante.getFormasPagamento()).isEmpty();
    }
}
