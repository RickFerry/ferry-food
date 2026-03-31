package com.ferry.food.domain.ports.input.estado;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;

public interface CriarEstadoUseCase {
    EstadoDTO executar(CriarEstadoInput input);

    record CriarEstadoInput(
        String nome
    ) {}
}
