package com.ferry.food.domain.ports.input.cidade;

import com.ferry.food.application.dtos.output.cidade.CidadeDTO;

public interface ObterCidadeUseCase {
    CidadeDTO executar(Long id);
}
