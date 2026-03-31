package com.ferry.food.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;

public record Endereco(
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String cep,
    Long cidadeId
) {
    public Endereco {
        if (logradouro == null || logradouro.isBlank()) {
            throw new ValidationException("Logradouro é obrigatório");
        }
        if (numero == null || numero.isBlank()) {
            throw new ValidationException("Número é obrigatório");
        }
        if (bairro == null || bairro.isBlank()) {
            throw new ValidationException("Bairro é obrigatório");
        }
        if (cep == null || cep.isBlank()) {
            throw new ValidationException("CEP é obrigatório");
        }
        if (cidadeId == null || cidadeId <= 0) {
            throw new ValidationException("Cidade é obrigatória");
        }
    }

    public boolean ehValido() {
        return logradouro != null && !logradouro.isBlank()
            && numero != null && !numero.isBlank()
            && bairro != null && !bairro.isBlank()
            && cep != null && !cep.isBlank()
            && cidadeId != null && cidadeId > 0;
    }
}
