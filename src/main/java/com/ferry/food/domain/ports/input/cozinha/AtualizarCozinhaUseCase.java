package com.ferry.food.domain.ports.input.cozinha;

import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;

public interface AtualizarCozinhaUseCase {
    CozinhaDTO executar(Long id, AtualizarCozinhaInput input);

    record AtualizarCozinhaInput(
        String nome
    ) {}
}
