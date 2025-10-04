package com.ferry.food.infrastructure.repository;

import com.ferry.food.model.Cozinha;
import com.ferry.food.repository.CozinhaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cozinha> listarCozinhas() {
        return em.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha buscarCozinha(Long id) {
        return em.find(Cozinha.class, id);
    }

    @Override
    @Transactional
    public Cozinha adicionarCozinha(Cozinha cozinha) {
        return em.merge(cozinha);
    }

    @Override
    @Transactional
    public void removerCozinha(Long id) {
        Cozinha cozinha = buscarCozinha(id);
        if (cozinha != null) {
            em.remove(cozinha);
        }
    }

    @Override
    @Transactional
    public Cozinha atualizarCozinha(Cozinha cozinha, Long id) {
        Cozinha cozinhaAtual = buscarCozinha(id);
        if (cozinhaAtual != null) {
            cozinha.setId(cozinhaAtual.getId());
            return em.merge(cozinha);
        }
        return null;
    }
}
