package com.ferry.food.application.usecases.restaurante;

import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.ports.input.restaurante.ListarRestaurantesUseCase;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import com.ferry.food.application.mappers.restaurante.RestauranteOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarRestaurantesImpl implements ListarRestaurantesUseCase {
    
    private final RestauranteRepositoryPort restauranteRepository;
    private final RestauranteOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<RestauranteDTO> executar() {
        return restauranteRepository.listarTodos()
            .stream()
            .map(outputMapper::toDTO)
            .toList();
    }
}
