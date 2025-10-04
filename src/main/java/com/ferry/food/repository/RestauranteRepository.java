package com.ferry.food.repository;

import com.ferry.food.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listarRestaurantes();
    Restaurante buscarRestaurante(Long id);
    Restaurante adicionar(Restaurante restaurante);
    void remover(Long id);
    Restaurante atualizar(Restaurante restaurante, Long id);
}
