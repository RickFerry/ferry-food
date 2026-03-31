package com.ferry.food.unit.domain.valueobjects;

import com.ferry.food.domain.valueobjects.StatusPedido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("StatusPedido Value Object Tests")
class TestStatusPedido {

    @Test
    @DisplayName("should convert StatusPedido enum to string")
    void testConversaoStatusPedido_DeveConverterString() {
        // Act & Assert
        assertThat(StatusPedido.CRIADO.toString()).isEqualTo("CRIADO");
        assertThat(StatusPedido.CONFIRMADO.toString()).isEqualTo("CONFIRMADO");
        assertThat(StatusPedido.ENTREGUE.toString()).isEqualTo("ENTREGUE");
        assertThat(StatusPedido.CANCELADO.toString()).isEqualTo("CANCELADO");
    }

    @Test
    @DisplayName("should get description for CRIADO status")
    void testObtenerDescricao_CRIADO() {
        // Act
        String descricao = StatusPedido.CRIADO.getDescricao();

        // Assert
        assertThat(descricao).isEqualTo("Criado");
    }

    @Test
    @DisplayName("should get details for CRIADO status")
    void testObtenerDetalhes_CRIADO() {
        // Act
        String detalhes = StatusPedido.CRIADO.getDetalhes();

        // Assert
        assertThat(detalhes).isEqualTo("Pedido recém criado");
    }

    @Test
    @DisplayName("should allow confirming when status is CRIADO")
    void testPodeSerConfirmado_ComStatusCRIADO_DeveRetornarTrue() {
        // Act & Assert
        assertThat(StatusPedido.CRIADO.podeSerConfirmado()).isTrue();
    }

    @Test
    @DisplayName("should not allow confirming when status is CONFIRMADO")
    void testPodeSerConfirmado_ComStatusCONFIRMADO_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.CONFIRMADO.podeSerConfirmado()).isFalse();
    }

    @Test
    @DisplayName("should not allow confirming when status is ENTREGUE")
    void testPodeSerConfirmado_ComStatusENTREGUE_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.ENTREGUE.podeSerConfirmado()).isFalse();
    }

    @Test
    @DisplayName("should not allow confirming when status is CANCELADO")
    void testPodeSerConfirmado_ComStatusCANCELADO_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.CANCELADO.podeSerConfirmado()).isFalse();
    }

    @Test
    @DisplayName("should allow delivering when status is CONFIRMADO")
    void testPodeSerEntregue_ComStatusCONFIRMADO_DeveRetornarTrue() {
        // Act & Assert
        assertThat(StatusPedido.CONFIRMADO.podeSerEntregue()).isTrue();
    }

    @Test
    @DisplayName("should not allow delivering when status is CRIADO")
    void testPodeSerEntregue_ComStatusCRIADO_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.CRIADO.podeSerEntregue()).isFalse();
    }

    @Test
    @DisplayName("should not allow delivering when status is ENTREGUE")
    void testPodeSerEntregue_ComStatusENTREGUE_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.ENTREGUE.podeSerEntregue()).isFalse();
    }

    @Test
    @DisplayName("should not allow delivering when status is CANCELADO")
    void testPodeSerEntregue_ComStatusCANCELADO_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.CANCELADO.podeSerEntregue()).isFalse();
    }

    @Test
    @DisplayName("should allow canceling when status is CRIADO")
    void testPodeCancelar_ComStatusCRIADO_DeveRetornarTrue() {
        // Act & Assert
        assertThat(StatusPedido.CRIADO.podeCancelar()).isTrue();
    }

    @Test
    @DisplayName("should allow canceling when status is CONFIRMADO")
    void testPodeCancelar_ComStatusCONFIRMADO_DeveRetornarTrue() {
        // Act & Assert
        assertThat(StatusPedido.CONFIRMADO.podeCancelar()).isTrue();
    }

    @Test
    @DisplayName("should not allow canceling when status is ENTREGUE")
    void testPodeCancelar_ComStatusENTREGUE_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.ENTREGUE.podeCancelar()).isFalse();
    }

    @Test
    @DisplayName("should not allow canceling when status is CANCELADO")
    void testPodeCancelar_ComStatusCANCELADO_DeveRetornarFalse() {
        // Act & Assert
        assertThat(StatusPedido.CANCELADO.podeCancelar()).isFalse();
    }

    @Test
    @DisplayName("should have valid enum values")
    void testStatusPedidoEnumValues_DeveConterTodosOsValores() {
        // Act & Assert
        assertThat(StatusPedido.values()).hasSize(4);
        assertThat(StatusPedido.values()).contains(
            StatusPedido.CRIADO,
            StatusPedido.CONFIRMADO,
            StatusPedido.ENTREGUE,
            StatusPedido.CANCELADO
        );
    }
}
