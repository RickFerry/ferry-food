package com.ferry.food.repository;

import com.ferry.food.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listarCozinhas();
    Cozinha buscarCozinha(Long id);
    Cozinha adicionarCozinha(Cozinha cozinha);
    void removerCozinha(Long id);
    Cozinha atualizarCozinha(Cozinha cozinha, Long id);
}
