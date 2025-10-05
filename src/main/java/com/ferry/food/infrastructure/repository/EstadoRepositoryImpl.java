package com.ferry.food.infrastructure.repository;

import com.ferry.food.model.Estado;
import com.ferry.food.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Estado> listarEstados() {
        return em.createQuery("select e from Estado e", Estado.class).getResultList();
    }

    @Override
    public Estado buscarEstado(Long id) {
        return em.find(Estado.class, id);
    }

    @Override
    @Transactional
    public Estado adicionarEstado(Estado estado) {
        return em.merge(estado);
    }

    @Override
    @Transactional
    public void removerEstado(Long id) {
        Estado estado = buscarEstado(id);
        if (estado != null) {
            em.remove(estado);
        }
    }

    @Override
    @Transactional
    public Estado atualizarEstado(Estado estado, Long id) {
        Estado estadoAtual = buscarEstado(id);
        if (estadoAtual != null) {
            estado.setId(estadoAtual.getId());
            return em.merge(estado);
        }
        return null;
    }
}
