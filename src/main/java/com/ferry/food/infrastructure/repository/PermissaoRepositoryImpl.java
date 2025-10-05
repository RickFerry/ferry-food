package com.ferry.food.infrastructure.repository;

import com.ferry.food.model.FormaPagamento;
import com.ferry.food.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<FormaPagamento> listarPermissoes() {
        return em.createQuery("select p from Permissao p", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento buscarPermissao(Long id) {
        return em.find(FormaPagamento.class, id);
    }

    @Override
    @Transactional
    public FormaPagamento adicionarPermissao(FormaPagamento permissao) {
        return em.merge(permissao);
    }

    @Override
    @Transactional
    public void removerPermissao(Long id) {
        FormaPagamento permissao = buscarPermissao(id);
        if (permissao != null) {
            em.remove(permissao);
        }
    }

    @Override
    @Transactional
    public FormaPagamento atualizarPermissao(FormaPagamento permissao, Long id) {
        FormaPagamento permissaoAtual = buscarPermissao(id);
        if (permissaoAtual != null) {
            permissao.setId(permissaoAtual.getId());
            return em.merge(permissao);
        }
        return null;
    }
}
