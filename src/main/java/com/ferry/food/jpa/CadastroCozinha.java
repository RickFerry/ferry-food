package com.ferry.food.jpa;

import com.ferry.food.model.Cozinha;
import org.springframework.stereotype.Component;

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
}
