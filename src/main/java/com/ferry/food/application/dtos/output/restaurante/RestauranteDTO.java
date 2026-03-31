package com.ferry.food.application.dtos.output.restaurante;

import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import java.math.BigDecimal;

public record RestauranteDTO(
    Long id,
    String nome,
    BigDecimal taxaFrete,
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cep,
    CidadeDTO cidade,
    CozinhaDTO cozinha
) {}
