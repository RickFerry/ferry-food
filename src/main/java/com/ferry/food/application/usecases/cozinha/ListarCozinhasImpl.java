package com.ferry.food.application.usecases.cozinha;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.ports.input.cozinha.ListarCozinhasUseCase;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import com.ferry.food.application.mappers.cozinha.CozinhaOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListarCozinhasImpl implements ListarCozinhasUseCase {
    
    private final CozinhaRepositoryPort cozinhaRepository;
    private final CozinhaOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<CozinhaDTO> executar() {
        return cozinhaRepository.listarTodas()
            .stream()
            .map(outputMapper::toDTO)
            .collect(Collectors.toList());
    }
}
