package com.ferry.food.api.service;

import com.ferry.food.domain.exception.EntityInUseException;
import com.ferry.food.domain.exception.StateNotFoundException;
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
        return estadoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Estado buscarPorId(Long id) {
        return getOrElseThrow(id);
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.saveAndFlush(estado);
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado) {
        Estado estadoAtual = getOrElseThrow(id);
        copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    @Transactional
    public void deletar(Long id) {
        estadoRepository.findById(id).ifPresentOrElse(
                estado -> {
                    try {
                        estadoRepository.delete(estado);
                        estadoRepository.flush();
                    } catch (DataIntegrityViolationException | PersistenceException e) {
                        throw new EntityInUseException(format(
                                "Estado de código %d não pode ser removido, pois está em uso", id));
                    }
                },
                () -> {
                    throw new StateNotFoundException(id);
                }
        );
    }

    private Estado getOrElseThrow(Long id) {
        return estadoRepository.findById(id).orElseThrow(() -> new StateNotFoundException(id));
    }
}
