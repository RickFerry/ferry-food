package com.ferry.food.application.dtos.input.restaurante;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CriarRestauranteDTO {
    @NotBlank(message = "Nome do restaurante é obrigatório")
    private String nome;

    @NotNull(message = "Taxa de frete é obrigatória")
    @DecimalMin(value = "0.00", message = "Taxa de frete não pode ser negativa")
    private BigDecimal taxaFrete;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @NotNull(message = "ID da cidade é obrigatório")
    private Long cidadeId;

    @NotNull(message = "ID da cozinha é obrigatório")
    private Long cozinhaId;
}
