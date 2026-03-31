package com.ferry.food.application.usecases.estado;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.domain.ports.input.estado.DeletarEstadoUseCase;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.domain.exceptions.EntityInUseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeletarEstadoImpl implements DeletarEstadoUseCase {
    
    private final EstadoRepositoryPort estadoRepository;
    
    @Override
    @Transactional
    public void executar(Long id) {
        Estado estado = estadoRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Estado", id));
        
        try {
            estadoRepository.deletar(id);
        } catch (Exception e) {
            throw new EntityInUseException("Estado não pode ser deletado pois está em uso");
        }
    }
}
