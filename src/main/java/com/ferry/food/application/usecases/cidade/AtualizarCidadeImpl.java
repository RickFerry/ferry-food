package com.ferry.food.application.usecases.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.entities.Estado;
import com.ferry.food.domain.ports.input.cidade.AtualizarCidadeUseCase;
import com.ferry.food.domain.ports.output.CidadeRepositoryPort;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import com.ferry.food.application.mappers.cidade.CidadeOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AtualizarCidadeImpl implements AtualizarCidadeUseCase {
    
    private final CidadeRepositoryPort cidadeRepository;
    private final EstadoRepositoryPort estadoRepository;
    private final CidadeOutputMapper outputMapper;
    
    @Override
    @Transactional
    public CidadeDTO executar(Long id, AtualizarCidadeInput input) {
        Cidade cidade = cidadeRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Cidade", id));
        
        Estado estado = estadoRepository.obterPorId(input.estadoId())
            .orElseThrow(() -> EntityNotFoundException.forEntity("Estado", input.estadoId()));
        
        cidade.atualizarNome(input.nome());
        cidade.atualizarEstado(estado);
        
        Cidade cidadeAtualizada = cidadeRepository.salvar(cidade);
        return outputMapper.toDTO(cidadeAtualizada);
    }
}
