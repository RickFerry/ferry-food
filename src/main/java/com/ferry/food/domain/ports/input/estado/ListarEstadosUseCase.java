package com.ferry.food.domain.ports.input.estado;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import java.util.List;

public interface ListarEstadosUseCase {
    List<EstadoDTO> executar();
}
