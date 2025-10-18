package com.ferry.food.infrastructure.repository;

import com.ferry.food.domain.model.Cidade;
import com.ferry.food.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cidade> listarCidades() {
        return em.createQuery("select c from Cidade c", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscarCidade(Long id) {
        return em.find(Cidade.class, id);
    }

    @Override
    @Transactional
    public Cidade adicionarCidade(Cidade cidade) {
        return em.merge(cidade);
    }

    @Override
    @Transactional
    public void removerCidade(Long id) {
        Cidade cidade = buscarCidade(id);
        if (cidade != null) {
            em.remove(cidade);
        }
    }

    @Override
    @Transactional
    public Cidade atualizarCidade(Cidade cidade, Long id) {
        Cidade cidadeAtual = buscarCidade(id);
        if (cidadeAtual != null) {
            cidade.setId(cidadeAtual.getId());
            return em.merge(cidade);
        }
        return null;
    }
}
