package com.ferry.food.application.usecases.estado;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.domain.ports.input.estado.ObterEstadoUseCase;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import com.ferry.food.application.mappers.estado.EstadoOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ObterEstadoImpl implements ObterEstadoUseCase {
    
    private final EstadoRepositoryPort estadoRepository;
    private final EstadoOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public EstadoDTO executar(Long id) {
        Estado estado = estadoRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Estado", id));
        return outputMapper.toDTO(estado);
    }
}
