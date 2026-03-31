package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Taxa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemPedido {
    private Long id;
    private Pedido pedido;
    private Produto produto;
    private Taxa precoUnitario;
    private Taxa precoTotal;
    private Integer quantidade;
    private String observacao;

    public static ItemPedido criar(
        Long id,
        Pedido pedido,
        Produto produto,
        java.math.BigDecimal precoUnitario,
        java.math.BigDecimal precoTotal,
        Integer quantidade,
        String observacao
    ) {
        return new ItemPedido(
            id,
            pedido,
            produto,
            new Taxa(precoUnitario),
            new Taxa(precoTotal),
            quantidade,
            observacao
        );
    }

    public static ItemPedido criarNovo(
        Pedido pedido,
        Produto produto,
        Integer quantidade,
        String observacao
    ) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0");
        }
        Taxa precoUnitario = produto.getPreco();
        Taxa precoTotal = precoUnitario.multiplicar(java.math.BigDecimal.valueOf(quantidade));

        return new ItemPedido(
            null,
            pedido,
            produto,
            precoUnitario,
            precoTotal,
            quantidade,
            observacao
        );
    }
}
