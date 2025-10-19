package com.ferry.food.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cozinha;
import com.ferry.food.domain.model.Restaurante;
import com.ferry.food.domain.repository.CozinhaRepository;
import com.ferry.food.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.util.ReflectionUtils.*;

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

    @Transactional
    public Restaurante atualizarParcial(Long id, Map<String, Object> campos) {
        Restaurante restauranteAtual = getRestauranteOrNull(id);
        if (restauranteAtual == null) {
            throw new MyEntityNotFoundException(
                    format("Restaurante de código %d não encontrado", id));
        }
        merge(campos, restauranteAtual);
        return this.atualizar(id, restauranteAtual);
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);

        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = findField(Restaurante.class, nomePropriedade);
            if (field == null) {
                throw new IllegalArgumentException(format("Propriedade %s não existe em Restaurante", nomePropriedade));
            }
            field.setAccessible(true);

            Object novoValor = getField(field, restauranteOrigem);

            try {
                setField(field, restauranteDestino, novoValor);
            } catch (NullPointerException | IllegalArgumentException | IllegalStateException ex) {
                throw new IllegalStateException(format("Error setting property '%s': %s", nomePropriedade, ex.getMessage()), ex);
            } catch (Exception ex) {
                throw new IllegalStateException(format("Unexpected error setting property '%s'", nomePropriedade), ex);
            }
        });
    }

    private Restaurante getRestauranteOrNull(Long id) {
        return restauranteRepository.buscarRestaurante(id);
    }

    private Cozinha getCozinhaOrNull(Long cozinhaId) {
        return cozinhaRepository.buscarCozinha(cozinhaId);
    }
}
