package com.ferry.food.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cozinha;
import com.ferry.food.domain.model.Restaurante;
import com.ferry.food.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.ferry.food.domain.repository.spec.RestauranteSpecFactory.comFreteGratis;
import static com.ferry.food.domain.repository.spec.RestauranteSpecFactory.nomeSemelhante;
import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.util.ReflectionUtils.*;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final CozinhaService cozinhaService;
    private final RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Restaurante> listarComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(nomeSemelhante(nome)));
    }

    @Transactional(readOnly = true)
    public Restaurante findFirst() {
        return restauranteRepository.findFirst().orElseThrow(() -> new MyEntityNotFoundException(
                "Nenhum restaurante encontrado"));
    }

    @Transactional(readOnly = true)
    public Restaurante buscarPorId(Long id) {
        return getRestauranteOrElseThrow(id);
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Cozinha cozinha = cozinhaService.getCozinhaOrElseThrow(restaurante.getCozinha().getId());
        restaurante.setCozinha(cozinha);
        return restauranteRepository.saveAndFlush(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteAtual = getRestauranteOrElseThrow(id);

        if (restaurante.getCozinha() != null && restaurante.getCozinha().getId() != null) {
            Cozinha cozinha = cozinhaService.getCozinhaOrElseThrow(restaurante.getCozinha().getId());
            copyProperties(restaurante, restauranteAtual,
                    "id", "cozinha", "formasPagamento", "endereco", "dataCadastro");
            restauranteAtual.setCozinha(cozinha);
        } else {
            copyProperties(restaurante, restauranteAtual,
                    "id", "cozinha", "formasPagamento", "endereco", "dataCadastro");
        }

        return restauranteRepository.saveAndFlush(restauranteAtual);
    }

    @Transactional
    public Restaurante atualizarParcial(Long id, Map<String, Object> campos) {
        Restaurante restauranteAtual = getRestauranteOrElseThrow(id);
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

    private Restaurante getRestauranteOrElseThrow(Long id) {
        return restauranteRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(
                format("Restaurante de código %d não encontrado", id)));
    }
}
