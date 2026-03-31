package com.ferry.food.application.usecases.estado;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.domain.ports.input.estado.ListarEstadosUseCase;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import com.ferry.food.application.mappers.estado.EstadoOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListarEstadosImpl implements ListarEstadosUseCase {
    
    private final EstadoRepositoryPort estadoRepository;
    private final EstadoOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<EstadoDTO> executar() {
        return estadoRepository.listarTodos()
            .stream()
            .map(outputMapper::toDTO)
            .collect(Collectors.toList());
    }
}
