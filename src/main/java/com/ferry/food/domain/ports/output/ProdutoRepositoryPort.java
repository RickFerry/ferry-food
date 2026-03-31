package com.ferry.food.domain.ports.output;

import com.ferry.food.domain.entities.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPort {
    Produto salvar(Produto produto);
    Optional<Produto> obterPorId(Long id);
    List<Produto> listarTodos();
    List<Produto> listarPorRestaurante(Long restauranteId);
    void deletar(Long id);
    boolean existePorId(Long id);
}
