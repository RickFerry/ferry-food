package com.ferry.food.domain.ports.input.estado;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import java.util.List;

/**
 * Input Port (Use Case Contract) for listing all Estados.
 *
 * Implemented by: Application layer use cases
 * Used by: HTTP controllers and other entry points
 *
 * This port defines the contract for retrieving all Estados from the system.
 * It abstracts the HTTP layer from use case implementation details,
 * enabling flexible testing and implementation changes.
 */
public interface ListarEstadosUseCase {
    List<EstadoDTO> executar();
}
