package com.ferry.food.domain.ports.input.cozinha;

import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import java.util.List;

public interface ListarCozinhasUseCase {
    List<CozinhaDTO> executar();
}
