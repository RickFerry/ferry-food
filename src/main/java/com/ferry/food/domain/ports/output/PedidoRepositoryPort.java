package com.ferry.food.domain.ports.output;

import com.ferry.food.domain.entities.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoRepositoryPort {
    Pedido salvar(Pedido pedido);
    Optional<Pedido> obterPorId(Long id);
    List<Pedido> listarTodos();
    List<Pedido> listarPorRestaurante(Long restauranteId);
    void deletar(Long id);
    boolean existePorId(Long id);
}
