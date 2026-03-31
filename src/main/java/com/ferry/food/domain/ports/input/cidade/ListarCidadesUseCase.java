package com.ferry.food.domain.ports.input.cidade;

import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import java.util.List;

public interface ListarCidadesUseCase {
    List<CidadeDTO> executar();
}
