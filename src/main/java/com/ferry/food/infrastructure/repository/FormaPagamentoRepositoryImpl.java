package com.ferry.food.infrastructure.repository;

import com.ferry.food.domain.model.FormaPagamento;
import com.ferry.food.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FormaPagamento> listarFormasPagamento() {
        return em.createQuery("select f from FormaPagamento f", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento buscarFormaPagamento(Long id) {
        return em.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public FormaPagamento adicionarFormaPagamento(FormaPagamento formaPagamento) {
        return em.merge(formaPagamento);
    }

    @Override
    @Transactional
    public void removerFormaPagamento(Long id) {
        FormaPagamento formaPagamento = buscarFormaPagamento(id);
        if (formaPagamento != null) {
            em.remove(formaPagamento);
        }
    }

    @Override
    @Transactional
    public FormaPagamento atualizarFormaPagamento(FormaPagamento formaPagamento, Long id) {
        FormaPagamento formaPagamentoAtual = buscarFormaPagamento(id);
        if (formaPagamentoAtual != null) {
            formaPagamento.setId(formaPagamentoAtual.getId());
            return em.merge(formaPagamento);
        }
        return null;
    }
}
