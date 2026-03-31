package com.ferry.food.application.usecases.cozinha;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.ports.input.cozinha.CriarCozinhaUseCase;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import com.ferry.food.application.mappers.cozinha.CozinhaOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CriarCozinhaImpl implements CriarCozinhaUseCase {
    
    private final CozinhaRepositoryPort cozinhaRepository;
    private final CozinhaOutputMapper outputMapper;
    
    @Override
    @Transactional
    public CozinhaDTO executar(CriarCozinhaInput input) {
        Cozinha cozinha = Cozinha.criarNova(input.nome());
        Cozinha cozinhaSalva = cozinhaRepository.salvar(cozinha);
        return outputMapper.toDTO(cozinhaSalva);
    }
}
