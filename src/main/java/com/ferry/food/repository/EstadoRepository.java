package com.ferry.food.repository;

import com.ferry.food.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listarEstados();
    Estado buscarEstado(Long id);
    Estado adicionarEstado(Estado estado);
    void removerEstado(Long id);
    Estado atualizarEstado(Estado estado, Long id);
}
