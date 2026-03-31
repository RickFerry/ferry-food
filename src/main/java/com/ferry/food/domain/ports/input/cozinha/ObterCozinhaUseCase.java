package com.ferry.food.domain.ports.input.cozinha;

import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;

public interface ObterCozinhaUseCase {
    CozinhaDTO executar(Long id);
}
