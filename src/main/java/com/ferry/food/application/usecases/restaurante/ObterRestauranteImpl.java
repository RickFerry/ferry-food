package com.ferry.food.application.usecases.restaurante;

import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.ports.input.restaurante.ObterRestauranteUseCase;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import com.ferry.food.application.mappers.restaurante.RestauranteOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ObterRestauranteImpl implements ObterRestauranteUseCase {
    
    private final RestauranteRepositoryPort restauranteRepository;
    private final RestauranteOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public RestauranteDTO executar(Long id) {
        Restaurante restaurante = restauranteRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Restaurante", id));
        return outputMapper.toDTO(restaurante);
    }
}
