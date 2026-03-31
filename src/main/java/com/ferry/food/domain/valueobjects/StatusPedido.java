package com.ferry.food.domain.valueobjects;

public enum StatusPedido {
    CRIADO("Criado", "Pedido recém criado"),
    CONFIRMADO("Confirmado", "Pedido confirmado pelo restaurante"),
    ENTREGUE("Entregue", "Pedido foi entregue"),
    CANCELADO("Cancelado", "Pedido foi cancelado");

    private final String descricao;
    private final String detalhes;

    StatusPedido(String descricao, String detalhes) {
        this.descricao = descricao;
        this.detalhes = detalhes;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public boolean podeSerConfirmado() {
        return this == CRIADO;
    }

    public boolean podeSerEntregue() {
        return this == CONFIRMADO;
    }

    public boolean podeCancelar() {
        return this == CRIADO || this == CONFIRMADO;
    }
}
