package com.ferry.food.service;

import com.ferry.food.model.Cozinha;
import com.ferry.food.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public List<Cozinha> listar() {
        return cozinhaRepository.listarCozinhas();
    }

    @Transactional(readOnly = true)
    public Cozinha buscarPorId(Long id) {
        return getCozinhaOrNull(id);
    }

    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return cozinhaRepository.adicionarCozinha(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long id, Cozinha cozinha) {
        Cozinha cozinhaAtual = getCozinhaOrNull(id);
        if (cozinhaAtual != null) {
            copyProperties(cozinha, cozinhaAtual, "id");
            return cozinhaAtual;
        }
        return null;
    }

    @Transactional
    public Object deletar(Long id) {
        Cozinha cozinha = getCozinhaOrNull(id);
        if (cozinha != null) {
            cozinhaRepository.removerCozinha(cozinha.getId());
        }
        return null;
    }

    private Cozinha getCozinhaOrNull(Long id) {
        return cozinhaRepository.buscarCozinha(id);
    }
}
