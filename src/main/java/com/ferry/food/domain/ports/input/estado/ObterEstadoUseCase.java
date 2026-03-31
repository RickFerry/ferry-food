package com.ferry.food.domain.ports.input.estado;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;

public interface ObterEstadoUseCase {
    EstadoDTO executar(Long id);
}
