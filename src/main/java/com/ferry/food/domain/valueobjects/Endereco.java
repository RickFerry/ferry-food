package com.ferry.food.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Endereco {
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cep;
    private final Long cidadeId;

    public Endereco(String logradouro, String numero, String complemento, String bairro, String cep, Long cidadeId) {
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

        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidadeId = cidadeId;
    }

    public boolean ehValido() {
        return logradouro != null && !logradouro.isBlank()
            && numero != null && !numero.isBlank()
            && bairro != null && !bairro.isBlank()
            && cep != null && !cep.isBlank()
            && cidadeId != null && cidadeId > 0;
    }
}
