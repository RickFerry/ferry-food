package com.ferry.food.domain.ports.input.cidade;

import com.ferry.food.application.dtos.output.cidade.CidadeDTO;

public interface AtualizarCidadeUseCase {
    CidadeDTO executar(Long id, AtualizarCidadeInput input);

    record AtualizarCidadeInput(
        String nome,
        Long estadoId
    ) {}
}
