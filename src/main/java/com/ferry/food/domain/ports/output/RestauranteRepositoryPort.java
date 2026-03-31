package com.ferry.food.domain.ports.output;

import com.ferry.food.domain.entities.Restaurante;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepositoryPort {
    Restaurante salvar(Restaurante restaurante);
    Optional<Restaurante> obterPorId(Long id);
    List<Restaurante> listarTodos();
    List<Restaurante> listarComFreteGratis();
    List<Restaurante> listarComFreteGratis(String nomeContendo);
    Restaurante obterPrimeiro();
    void deletar(Long id);
    boolean existePorId(Long id);
}
