package com.ferry.food.domain.ports.input.cozinha;

import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;

public interface CriarCozinhaUseCase {
    CozinhaDTO executar(CriarCozinhaInput input);

    record CriarCozinhaInput(
        String nome
    ) {}
}
