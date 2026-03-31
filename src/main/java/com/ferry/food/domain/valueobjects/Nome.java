package com.ferry.food.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Nome {
    private final String valor;

    public Nome(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new ValidationException("Nome não pode ser vazio");
        }
        if (valor.length() < 3) {
            throw new ValidationException("Nome deve ter no mínimo 3 caracteres");
        }
        if (valor.length() > 255) {
            throw new ValidationException("Nome não pode exceder 255 caracteres");
        }
        this.valor = valor;
    }

    public boolean contemPalavra(String palavra) {
        if (palavra == null || palavra.trim().isEmpty()) {
            return false;
        }
        return valor.toLowerCase().contains(palavra.toLowerCase());
    }

    public String paraExibicao() {
        return valor.trim();
    }
}
