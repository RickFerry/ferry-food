package com.ferry.food.api.service;

import com.ferry.food.domain.exception.EntityInUseException;
import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Estado;
import com.ferry.food.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private final EstadoRepository estadoRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Estado> listar() {
        return estadoRepository.listarEstados();
    }

    @Transactional(readOnly = true)
    public Estado buscarPorId(Long id) {
        Estado estado = getEstadoAtualOrNull(id);
        if (estado != null) {
            return estado;
        }
        throw new MyEntityNotFoundException(format("Estado de código %d não encontrado", id));
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.adicionarEstado(estado);
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado) {
        Estado estadoAtual = getEstadoAtualOrNull(id);
        if (estadoAtual != null) {
            copyProperties(estado, estadoAtual, "id");
            return estadoRepository.adicionarEstado(estadoAtual);
        }
        throw new MyEntityNotFoundException(format("Estado de código %d não encontrado", id));
    }

    @Transactional
    public void deletar(Long id) {
        Estado estado = getEstadoAtualOrNull(id);
        if (estado != null) {
            try {
                estadoRepository.removerEstado(estado.getId());
                entityManager.flush();
            } catch (DataIntegrityViolationException | PersistenceException e) {
                throw new EntityInUseException(format(
                        "Estado de código %d não pode ser removido, pois está em uso", id));
            }
        }
        throw new MyEntityNotFoundException(format("Estado de código %d não encontrado", id));
    }

    private Estado getEstadoAtualOrNull(Long id) {
        return estadoRepository.buscarEstado(id);
    }
}
