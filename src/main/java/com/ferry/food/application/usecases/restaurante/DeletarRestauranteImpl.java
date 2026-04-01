package com.ferry.food.application.usecases.restaurante;

import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.ports.input.restaurante.DeletarRestauranteUseCase;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.domain.exceptions.EntityInUseException;
import com.ferry.food.domain.domainservices.ValidadorRestaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeletarRestauranteImpl implements DeletarRestauranteUseCase {
    
    private final RestauranteRepositoryPort restauranteRepository;
    private final ValidadorRestaurante validador;
    
    @Override
    @Transactional
    public void executar(Long id) {
        Restaurante restaurante = restauranteRepository.obterPorId(id)
            .orElseThrow(() -> EntityNotFoundException.forEntity("Restaurante", id));
        
        validador.validarParaDelecao(restaurante);
        
        try {
            restauranteRepository.deletar(id);
        } catch (Exception e) {
            throw new EntityInUseException("Restaurante não pode ser deletado pois está em uso");
        }
    }
}
