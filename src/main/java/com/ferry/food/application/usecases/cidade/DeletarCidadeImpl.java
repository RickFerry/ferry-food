package com.ferry.food.application.usecases.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.ports.input.cidade.DeletarCidadeUseCase;
import com.ferry.food.domain.ports.output.CidadeRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.domain.exceptions.EntityInUseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeletarCidadeImpl implements DeletarCidadeUseCase {
    
    private final CidadeRepositoryPort cidadeRepository;
    
    @Override
    @Transactional
    public void executar(Long id) {
        Cidade cidade = cidadeRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Cidade", id));
        
        try {
            cidadeRepository.deletar(id);
        } catch (Exception e) {
            throw new EntityInUseException("Cidade não pode ser deletada pois está em uso");
        }
    }
}
