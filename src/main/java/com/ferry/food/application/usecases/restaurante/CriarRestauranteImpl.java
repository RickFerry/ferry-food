package com.ferry.food.application.usecases.restaurante;

import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.ports.input.restaurante.CriarRestauranteUseCase;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.domain.exceptions.EntityNotFoundException;
import com.ferry.food.domain.domainservices.ValidadorRestaurante;
import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import com.ferry.food.application.mappers.restaurante.RestauranteOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CriarRestauranteImpl implements CriarRestauranteUseCase {
    
    private final RestauranteRepositoryPort restauranteRepository;
    private final CozinhaRepositoryPort cozinhaRepository;
    private final RestauranteOutputMapper outputMapper;
    private final ValidadorRestaurante validador;
    
    @Override
    @Transactional
    public RestauranteDTO executar(CriarRestauranteInput input) {
        Cozinha cozinha = cozinhaRepository.obterPorId(input.cozinhaId())
            .orElseThrow(() -> EntityNotFoundException.forEntity("Cozinha", input.cozinhaId()));
        
        validador.validarParaCriacao(input.nome(), cozinha);
        
        Restaurante restaurante = Restaurante.criarNovo(
            input.nome(),
            input.taxaFrete(),
            input.logradouro(),
            input.numero(),
            input.complemento(),
            input.bairro(),
            input.cep(),
            input.cidadeId(),
            cozinha
        );
        
        Restaurante restauranteSalvo = restauranteRepository.salvar(restaurante);
        return outputMapper.toDTO(restauranteSalvo);
    }
}
