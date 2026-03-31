package com.ferry.food.domain.ports.input.estado;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;

public interface AtualizarEstadoUseCase {
    EstadoDTO executar(Long id, AtualizarEstadoInput input);

    record AtualizarEstadoInput(
        String nome
    ) {}
}
