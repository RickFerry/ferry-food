package com.ferry.food.domain.ports.output;

import com.ferry.food.domain.entities.Cozinha;
import java.util.List;
import java.util.Optional;

public interface CozinhaRepositoryPort {
    Cozinha salvar(Cozinha cozinha);
    Optional<Cozinha> obterPorId(Long id);
    List<Cozinha> listarTodas();
    void deletar(Long id);
    boolean existePorId(Long id);
}
