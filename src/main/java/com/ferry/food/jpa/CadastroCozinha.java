package com.ferry.food.jpa;

import com.ferry.food.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CadastroCozinha {
    @PersistenceContext
    private EntityManager em;

    public List<Cozinha> listarCozinhas(){
        return em.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    public Cozinha buscarCozinha(Long id){
        return em.find(Cozinha.class, id);
    }

    @Transactional
    public Cozinha adicionarCozinha(Cozinha cozinha){
        return em.merge(cozinha);
    }

    @Transactional
    public void removerCozinha(Long id){
        Cozinha cozinha = buscarCozinha(id);
        if (cozinha != null) {
            em.remove(cozinha);
        }
    }

    @Transactional
    public Cozinha atualizarCozinha(Cozinha cozinha, Long id){
        Cozinha cozinhaAtual = buscarCozinha(id);
        if (cozinhaAtual != null) {
            cozinha.setId(cozinhaAtual.getId());
            return em.merge(cozinha);
        }
        return null;
    }
}
