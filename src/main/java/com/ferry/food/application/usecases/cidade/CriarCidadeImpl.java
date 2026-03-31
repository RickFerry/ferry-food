package com.ferry.food.application.usecases.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.entities.Estado;
import com.ferry.food.domain.ports.input.cidade.CriarCidadeUseCase;
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
public class CriarCidadeImpl implements CriarCidadeUseCase {
    
    private final CidadeRepositoryPort cidadeRepository;
    private final EstadoRepositoryPort estadoRepository;
    private final CidadeOutputMapper outputMapper;
    
    @Override
    @Transactional
    public CidadeDTO executar(CriarCidadeInput input) {
        Estado estado = estadoRepository.obterPorId(input.estadoId())
            .orElseThrow(() -> EntityNotFoundException.forEntity("Estado", input.estadoId()));
        
        Cidade cidade = Cidade.criarNova(input.nome(), estado);
        Cidade cidadeSalva = cidadeRepository.salvar(cidade);
        
        return outputMapper.toDTO(cidadeSalva);
    }
}
