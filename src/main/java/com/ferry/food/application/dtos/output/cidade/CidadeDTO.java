package com.ferry.food.application.dtos.output.cidade;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;

public record CidadeDTO(
    Long id,
    String nome,
    EstadoDTO estado
) {}
