package com.ferry.food.domain.repository;

import com.ferry.food.domain.model.FormaPagamento;

import java.util.List;

public interface PermissaoRepository {
    List<FormaPagamento> listarPermissoes();
    FormaPagamento buscarPermissao(Long id);
    FormaPagamento adicionarPermissao(FormaPagamento permissao);
    void removerPermissao(Long id);
    FormaPagamento atualizarPermissao(FormaPagamento permissao, Long id);
}
