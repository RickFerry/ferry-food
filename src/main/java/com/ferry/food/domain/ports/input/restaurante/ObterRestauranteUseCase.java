package com.ferry.food.domain.ports.input.restaurante;

import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;

public interface ObterRestauranteUseCase {
    RestauranteDTO executar(Long id);
}
