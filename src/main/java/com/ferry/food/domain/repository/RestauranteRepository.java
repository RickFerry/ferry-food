package com.ferry.food.domain.repository;

import com.ferry.food.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listarRestaurantes();
    Restaurante buscarRestaurante(Long id);
    Restaurante adicionar(Restaurante restaurante);
    void remover(Long id);
    Restaurante atualizar(Restaurante restaurante, Long id);
}
