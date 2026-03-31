package com.ferry.food.application.usecases.cozinha;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.ports.input.cozinha.ObterCozinhaUseCase;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import com.ferry.food.application.mappers.cozinha.CozinhaOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ObterCozinhaImpl implements ObterCozinhaUseCase {
    
    private final CozinhaRepositoryPort cozinhaRepository;
    private final CozinhaOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public CozinhaDTO executar(Long id) {
        Cozinha cozinha = cozinhaRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Cozinha", id));
        return outputMapper.toDTO(cozinha);
    }
}
