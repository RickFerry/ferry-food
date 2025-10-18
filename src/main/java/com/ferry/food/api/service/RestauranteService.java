package com.ferry.food.api.service;

import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cozinha;
import com.ferry.food.domain.model.Restaurante;
import com.ferry.food.domain.repository.CozinhaRepository;
import com.ferry.food.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final CozinhaRepository cozinhaRepository;
    private final RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<Restaurante> listar() {
        return restauranteRepository.listarRestaurantes();
    }

    @Transactional(readOnly = true)
    public Restaurante buscarPorId(Long id) {
        Restaurante restaurante = getRestauranteOrNull(id);
        if (restaurante != null) {
            return restaurante;
        }
        throw new MyEntityNotFoundException(
                format("Restaurante de código %d não encontrado", id));
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = getCozinhaOrNull(cozinhaId);
        if (cozinha == null) {
            throw new MyEntityNotFoundException(
                    format("Cozinha de código %d não encontrada", cozinhaId));
        }
        restaurante.setCozinha(cozinha);
        return restauranteRepository.adicionar(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteAtual = getRestauranteOrNull(id);
        if (restauranteAtual != null) {
            Long cozinhaId = restaurante.getCozinha().getId();
            Cozinha cozinha = getCozinhaOrNull(cozinhaId);
            if (cozinha == null) {
                throw new MyEntityNotFoundException(
                        format("Cozinha de código %d não encontrada", cozinhaId));
            }
            copyProperties(restaurante, restauranteAtual, "id", "cozinha");
            restauranteAtual.setCozinha(cozinha);
            return restauranteAtual;
        }
        throw new MyEntityNotFoundException(
                format("Restaurante de código %d não encontrado", id));
    }

    private Restaurante getRestauranteOrNull(Long id) {
        return restauranteRepository.buscarRestaurante(id);
    }

    private Cozinha getCozinhaOrNull(Long cozinhaId) {
        return cozinhaRepository.buscarCozinha(cozinhaId);
    }
}
