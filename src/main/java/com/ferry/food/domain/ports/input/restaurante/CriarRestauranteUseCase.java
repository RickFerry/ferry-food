package com.ferry.food.domain.ports.input.restaurante;

import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;

public interface CriarRestauranteUseCase {
    RestauranteDTO executar(CriarRestauranteInput input);

    record CriarRestauranteInput(
        String nome,
        java.math.BigDecimal taxaFrete,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Long cidadeId,
        Long cozinhaId
    ) {}
}
