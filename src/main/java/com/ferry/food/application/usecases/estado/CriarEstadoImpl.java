package com.ferry.food.application.usecases.estado;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.domain.ports.input.estado.CriarEstadoUseCase;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import com.ferry.food.application.mappers.estado.EstadoOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CriarEstadoImpl implements CriarEstadoUseCase {
    
    private final EstadoRepositoryPort estadoRepository;
    private final EstadoOutputMapper outputMapper;
    
    @Override
    @Transactional
    public EstadoDTO executar(CriarEstadoInput input) {
        Estado estado = Estado.criarNovo(input.nome());
        Estado estadoSalvo = estadoRepository.salvar(estado);
        return outputMapper.toDTO(estadoSalvo);
    }
}
