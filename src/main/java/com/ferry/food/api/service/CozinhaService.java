package com.ferry.food.api.service;

import com.ferry.food.domain.exception.EntityInUseException;
import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cozinha;
import com.ferry.food.domain.repository.CozinhaRepository;
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
public class CozinhaService {
    private static final CozinhaRepository cozinhaRepository = null;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cozinha buscarPorId(Long id) {
        return getCozinhaOrElseThrow(id);
    }

    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return cozinhaRepository.saveAndFlush(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long id, Cozinha cozinha) {
        Cozinha cozinhaAtual = getCozinhaOrElseThrow(id);
        copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaAtual;
    }

    @Transactional
    public void deletar(Long id) {
        cozinhaRepository.findById(id).ifPresentOrElse(
                cozinha -> {
                    try {
                        cozinhaRepository.delete(cozinha);
                        cozinhaRepository.flush();
                    } catch (DataIntegrityViolationException | PersistenceException e) {
                        throw new EntityInUseException(format(
                                "Cozinha de código %d não pode ser removida, pois está em uso", id));
                    }
                },
                () -> {
                    throw new MyEntityNotFoundException(format("Cozinha de código %d não encontrada", id));
                }
        );
    }

    public static Cozinha getCozinhaOrElseThrow(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(() ->
                new MyEntityNotFoundException(format("Cozinha de código %d não encontrada", id)));
    }
}
