package com.ferry.food.application.usecases.cozinha;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.ports.input.cozinha.DeletarCozinhaUseCase;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.domain.exceptions.EntityInUseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeletarCozinhaImpl implements DeletarCozinhaUseCase {
    
    private final CozinhaRepositoryPort cozinhaRepository;
    
    @Override
    @Transactional
    public void executar(Long id) {
        Cozinha cozinha = cozinhaRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Cozinha", id));
        
        try {
            cozinhaRepository.deletar(id);
        } catch (Exception e) {
            throw new EntityInUseException("Cozinha não pode ser deletada pois está em uso");
        }
    }
}
