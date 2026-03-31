package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Endereco;
import com.ferry.food.domain.valueobjects.StatusPedido;
import com.ferry.food.domain.valueobjects.Taxa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Pedido {
    private Long id;
    private Taxa subtotal;
    private Taxa taxaFrete;
    private Taxa valorTotal;
    private StatusPedido status;
    private Endereco endereco;
    private Restaurante restaurante;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataEntrega;
    private LocalDateTime dataCancelamento;
    private List<ItemPedido> itens;

    public static Pedido criarNovo(
        Taxa subtotal,
        Taxa taxaFrete,
        Endereco endereco,
        Restaurante restaurante
    ) {
        Taxa valorTotal = subtotal.somar(taxaFrete);
        return new Pedido(
            null,
            subtotal,
            taxaFrete,
            valorTotal,
            StatusPedido.CRIADO,
            endereco,
            restaurante,
            LocalDateTime.now(),
            null,
            null,
            null,
            new ArrayList<>()
        );
    }

    public void confirmar() {
        if (!status.podeSerConfirmado()) {
            throw new IllegalStateException("Pedido com status " + status + " não pode ser confirmado");
        }
        this.status = StatusPedido.CONFIRMADO;
        this.dataConfirmacao = LocalDateTime.now();
    }

    public void entregar() {
        if (!status.podeSerEntregue()) {
            throw new IllegalStateException("Pedido com status " + status + " não pode ser entregue");
        }
        this.status = StatusPedido.ENTREGUE;
        this.dataEntrega = LocalDateTime.now();
    }

    public void cancelar() {
        if (!status.podeCancelar()) {
            throw new IllegalStateException("Pedido com status " + status + " não pode ser cancelado");
        }
        this.status = StatusPedido.CANCELADO;
        this.dataCancelamento = LocalDateTime.now();
    }

    public void adicionarItem(ItemPedido item) {
        if (item == null) {
            throw new IllegalArgumentException("Item não pode ser nulo");
        }
        this.itens.add(item);
    }

    public boolean emPreparacao() {
        return status == StatusPedido.CONFIRMADO;
    }
}
