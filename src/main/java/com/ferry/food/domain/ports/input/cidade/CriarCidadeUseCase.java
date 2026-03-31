package com.ferry.food.domain.ports.input.cidade;

import com.ferry.food.application.dtos.output.cidade.CidadeDTO;

public interface CriarCidadeUseCase {
    CidadeDTO executar(CriarCidadeInput input);

    record CriarCidadeInput(
        String nome,
        Long estadoId
    ) {}
}
