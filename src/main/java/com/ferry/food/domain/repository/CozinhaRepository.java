package com.ferry.food.domain.repository;

import com.ferry.food.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listarCozinhas();
    Cozinha buscarCozinha(Long id);
    Cozinha adicionarCozinha(Cozinha cozinha);
    void removerCozinha(Long id);
    Cozinha atualizarCozinha(Cozinha cozinha, Long id);
}
