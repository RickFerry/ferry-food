package com.ferry.food.domain.repository;

import com.ferry.food.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listarEstados();
    Estado buscarEstado(Long id);
    Estado adicionarEstado(Estado estado);
    void removerEstado(Long id);
    Estado atualizarEstado(Estado estado, Long id);
}
