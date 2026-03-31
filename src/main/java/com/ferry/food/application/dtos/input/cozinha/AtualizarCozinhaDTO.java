package com.ferry.food.application.dtos.input.cozinha;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AtualizarCozinhaDTO {
    @NotBlank(message = "Nome da cozinha é obrigatório")
    private String nome;
}
