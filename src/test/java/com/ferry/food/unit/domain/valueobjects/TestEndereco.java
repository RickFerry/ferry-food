package com.ferry.food.unit.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;
import com.ferry.food.domain.valueobjects.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Endereco Value Object Tests")
class TestEndereco {

    @Test
    @DisplayName("should create valid Endereco with all required fields")
    void testEnderecoValido_DeveSerCriado() {
        // Arrange
        String logradouro = "Rua das Flores";
        String numero = "123";
        String complemento = "Apto 456";
        String bairro = "Centro";
        String cep = "01310-100";
        Long cidadeId = 1L;

        // Act
        Endereco endereco = new Endereco(logradouro, numero, complemento, bairro, cep, cidadeId);

        // Assert
        assertThat(endereco).isNotNull();
        assertThat(endereco.getLogradouro()).isEqualTo(logradouro);
        assertThat(endereco.getNumero()).isEqualTo(numero);
        assertThat(endereco.getComplemento()).isEqualTo(complemento);
        assertThat(endereco.getBairro()).isEqualTo(bairro);
        assertThat(endereco.getCep()).isEqualTo(cep);
        assertThat(endereco.getCidadeId()).isEqualTo(cidadeId);
    }

    @Test
    @DisplayName("should throw ValidationException when logradouro is empty")
    void testEnderecoComLogradouroVazio_DeveLançarException() {
        // Arrange
        String logradouroVazio = "";

        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            logradouroVazio, "123", "Apto 456", "Centro", "01310-100", 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Logradouro é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when logradouro is null")
    void testEnderecoComLogradouroNulo_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            null, "123", "Apto 456", "Centro", "01310-100", 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Logradouro é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when numero is empty")
    void testEnderecoComNumeroVazio_DeveLançarException() {
        // Arrange
        String numeroVazio = "";

        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", numeroVazio, "Apto 456", "Centro", "01310-100", 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Número é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when numero is null")
    void testEnderecoComNumeroNulo_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", null, "Apto 456", "Centro", "01310-100", 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Número é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when bairro is empty")
    void testEnderecoComBairroVazio_DeveLançarException() {
        // Arrange
        String bairroVazio = "";

        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", "123", "Apto 456", bairroVazio, "01310-100", 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Bairro é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when bairro is null")
    void testEnderecoComBairroNulo_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", "123", "Apto 456", null, "01310-100", 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Bairro é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when cep is empty")
    void testEnderecoComCepVazio_DeveLançarException() {
        // Arrange
        String cepVazio = "";

        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", cepVazio, 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("CEP é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when cep is null")
    void testEnderecoComCepNulo_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", null, 1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("CEP é obrigatório");
    }

    @Test
    @DisplayName("should throw ValidationException when cidadeId is null")
    void testEnderecoComCidadeIdNula_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100", null
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Cidade é obrigatória");
    }

    @Test
    @DisplayName("should throw ValidationException when cidadeId is invalid (zero)")
    void testEnderecoComCidadeIdZero_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100", 0L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Cidade é obrigatória");
    }

    @Test
    @DisplayName("should throw ValidationException when cidadeId is invalid (negative)")
    void testEnderecoComCidadeIdNegativa_DeveLançarException() {
        // Act & Assert
        assertThatThrownBy(() -> new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100", -1L
        ))
            .isInstanceOf(ValidationException.class)
            .hasMessageContaining("Cidade é obrigatória");
    }

    @Test
    @DisplayName("should allow empty complemento")
    void testEnderecoComComplementoVazio_DeveSerValido() {
        // Arrange
        String complementoVazio = "";

        // Act
        Endereco endereco = new Endereco(
            "Rua das Flores", "123", complementoVazio, "Centro", "01310-100", 1L
        );

        // Assert
        assertThat(endereco).isNotNull();
    }

    @Test
    @DisplayName("should validate if endereco is valid")
    void testEhValido_DeveRetornarTrue() {
        // Arrange
        Endereco endereco = new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100", 1L
        );

        // Act & Assert
        assertThat(endereco.ehValido()).isTrue();
    }

    @Test
    @DisplayName("should be equal when comparing identical Endereco values")
    void testEquality_EnderecosComMesmosValores_DeveSerIguais() {
        // Arrange
        Endereco endereco1 = new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100", 1L
        );
        Endereco endereco2 = new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100", 1L
        );

        // Act & Assert
        assertThat(endereco1).isEqualTo(endereco2);
    }

    @Test
    @DisplayName("should not be equal when comparing different Endereco values")
    void testEquality_EnderecosComValoresDiferentes_DeveSerDiferentes() {
        // Arrange
        Endereco endereco1 = new Endereco(
            "Rua das Flores", "123", "Apto 456", "Centro", "01310-100", 1L
        );
        Endereco endereco2 = new Endereco(
            "Rua das Flores", "456", "Apto 456", "Centro", "01310-100", 1L
        );

        // Act & Assert
        assertThat(endereco1).isNotEqualTo(endereco2);
    }
}
