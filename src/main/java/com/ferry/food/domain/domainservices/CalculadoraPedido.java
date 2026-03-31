package com.ferry.food.domain.domainservices;

import com.ferry.food.domain.entities.Pedido;
import com.ferry.food.domain.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraPedido {

    public void validarPedido(Pedido pedido) {
        if (pedido == null) {
            throw new BusinessException("Pedido não pode ser nulo");
        }

        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new BusinessException("Pedido deve conter pelo menos um item");
        }

        if (pedido.getRestaurante() == null) {
            throw new BusinessException("Restaurante do pedido é obrigatório");
        }

        if (pedido.getEndereco() == null) {
            throw new BusinessException("Endereço de entrega é obrigatório");
        }
    }

    public void validarTransicaoDeStatus(Pedido pedido) {
        if (pedido == null) {
            throw new BusinessException("Pedido não pode ser nulo");
        }

        if (!pedido.getStatus().podeSerConfirmado() && 
            !pedido.getStatus().podeSerEntregue() && 
            !pedido.getStatus().podeCancelar()) {
            throw new BusinessException("Status inválido para o pedido");
        }
    }
}
