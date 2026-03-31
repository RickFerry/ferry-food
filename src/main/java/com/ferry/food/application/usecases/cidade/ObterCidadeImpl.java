package com.ferry.food.application.usecases.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.ports.input.cidade.ObterCidadeUseCase;
import com.ferry.food.domain.ports.output.CidadeRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import com.ferry.food.application.mappers.cidade.CidadeOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ObterCidadeImpl implements ObterCidadeUseCase {
    
    private final CidadeRepositoryPort cidadeRepository;
    private final CidadeOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public CidadeDTO executar(Long id) {
        Cidade cidade = cidadeRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Cidade", id));
        return outputMapper.toDTO(cidade);
    }
}
