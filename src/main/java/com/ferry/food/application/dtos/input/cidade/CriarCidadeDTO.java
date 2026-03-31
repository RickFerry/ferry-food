package com.ferry.food.application.dtos.input.cidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CriarCidadeDTO {
    @NotBlank(message = "Nome da cidade é obrigatório")
    private String nome;

    @NotNull(message = "ID do estado é obrigatório")
    private Long estadoId;
}
