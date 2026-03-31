package com.ferry.food.domain.ports.input.restaurante;

import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import java.util.List;

public interface ListarRestaurantesUseCase {
    List<RestauranteDTO> executar();
}
