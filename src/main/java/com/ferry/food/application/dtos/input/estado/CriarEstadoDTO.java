package com.ferry.food.application.dtos.input.estado;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CriarEstadoDTO {
    @NotBlank(message = "Nome do estado é obrigatório")
    private String nome;
}
