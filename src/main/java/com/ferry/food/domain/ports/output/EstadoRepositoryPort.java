package com.ferry.food.domain.ports.output;

import com.ferry.food.domain.entities.Estado;
import java.util.List;
import java.util.Optional;

/**
 * Output Port (Repository Contract) for Estado persistence.
 *
 * Implemented by: Adapter layer (EstadoRepositoryAdapter)
 * Used by: Application layer use cases for data persistence
 *
 * This port defines the contract for persisting and retrieving Estados.
 * It abstracts the domain layer from infrastructure concerns (database, ORM),
 * enabling easy switching between persistence technologies.
 *
 * All operations work with domain entities, never database-specific objects.
 */
public interface EstadoRepositoryPort {
    Estado salvar(Estado estado);
    Optional<Estado> obterPorId(Long id);
    List<Estado> listarTodos();
    void deletar(Long id);
    boolean existePorId(Long id);
}
