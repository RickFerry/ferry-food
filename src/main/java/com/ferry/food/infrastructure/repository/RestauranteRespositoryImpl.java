package com.ferry.food.infrastructure.repository;

import com.ferry.food.domain.model.Restaurante;
import com.ferry.food.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRespositoryImpl implements RestauranteRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Restaurante> listarRestaurantes() {
        return em.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscarRestaurante(Long id) {
        return em.find(Restaurante.class, id);
    }

    @Override
    @Transactional
    public Restaurante adicionar(Restaurante restaurante) {
        return em.merge(restaurante);
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Restaurante restaurante = buscarRestaurante(id);
        if (restaurante != null) {
            em.remove(restaurante);
        }
    }

    @Override
    @Transactional
    public Restaurante atualizar(Restaurante restaurante, Long id) {
        Restaurante restauranteAtual = buscarRestaurante(id);
        if (restauranteAtual != null) {
            restaurante.setId(restauranteAtual.getId());
            return em.merge(restaurante);
        }
        return null;
    }
}
