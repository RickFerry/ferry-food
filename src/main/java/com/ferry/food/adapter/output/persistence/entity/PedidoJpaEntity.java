package com.ferry.food.adapter.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class PedidoJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "subtotal")
    public BigDecimal subtotal;

    @Column(name = "taxa_frete")
    public BigDecimal taxaFrete;

    @Column(name = "valor_total")
    public BigDecimal valorTotal;

    @Column(name = "status")
    public String status;

    @Column(name = "data_criacao")
    public LocalDateTime dataCriacao;

    @Column(name = "data_confirmacao")
    public LocalDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    public LocalDateTime dataCancelamento;

    @Column(name = "data_entrega")
    public LocalDateTime dataEntrega;

    @Column(name = "forma_pagamento_id", nullable = false)
    public Long formaPagamentoId;

    @Column(name = "restaurante_id", nullable = false)
    public Long restauranteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", insertable = false, updatable = false)
    public RestauranteJpaEntity restaurante;

    @Column(name = "cliente_id", nullable = false)
    public Long clienteId;

    @Column(name = "cidade_id")
    public Long cidadeId;

    @Embedded
    public EnderecoEmbeddable endereco;
}
