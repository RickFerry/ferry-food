package com.ferry.food.domain.repository;

import com.ferry.food.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listarCidades();
    Cidade buscarCidade(Long id);
    Cidade adicionarCidade(Cidade cidade);
    void removerCidade(Long id);
    Cidade atualizarCidade(Cidade cidade, Long id);
}
