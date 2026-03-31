package com.ferry.food.application.usecases.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.ports.input.cidade.ListarCidadesUseCase;
import com.ferry.food.domain.ports.output.CidadeRepositoryPort;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import com.ferry.food.application.mappers.cidade.CidadeOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ListarCidadesImpl implements ListarCidadesUseCase {
    
    private final CidadeRepositoryPort cidadeRepository;
    private final CidadeOutputMapper outputMapper;
    
    @Override
    @Transactional(readOnly = true)
    public List<CidadeDTO> executar() {
        return cidadeRepository.listarTodas()
            .stream()
            .map(outputMapper::toDTO)
            .toList();
    }
}
