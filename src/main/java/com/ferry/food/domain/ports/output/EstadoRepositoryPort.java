package com.ferry.food.domain.ports.output;

import com.ferry.food.domain.entities.Estado;
import java.util.List;
import java.util.Optional;

public interface EstadoRepositoryPort {
    Estado salvar(Estado estado);
    Optional<Estado> obterPorId(Long id);
    List<Estado> listarTodos();
    void deletar(Long id);
    boolean existePorId(Long id);
}
