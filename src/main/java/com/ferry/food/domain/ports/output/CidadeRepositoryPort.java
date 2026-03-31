package com.ferry.food.domain.ports.output;

import com.ferry.food.domain.entities.Cidade;
import java.util.List;
import java.util.Optional;

public interface CidadeRepositoryPort {
    Cidade salvar(Cidade cidade);
    Optional<Cidade> obterPorId(Long id);
    List<Cidade> listarTodas();
    List<Cidade> listarPorEstado(Long estadoId);
    void deletar(Long id);
    boolean existePorId(Long id);
}
