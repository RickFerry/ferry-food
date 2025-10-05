package com.ferry.food.repository;

import com.ferry.food.model.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> listarFormasPagamento();
    FormaPagamento buscarFormaPagamento(Long id);
    FormaPagamento adicionarFormaPagamento(FormaPagamento formaPagamento);
    void removerFormaPagamento(Long id);
    FormaPagamento atualizarFormaPagamento(FormaPagamento formaPagamento, Long id);
}
