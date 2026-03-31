package com.ferry.food.domain.valueobjects;

import com.ferry.food.domain.exceptions.ValidationException;

public record Nome(
    String valor
) {
    public Nome {
        if (valor == null || valor.isBlank()) {
            throw new ValidationException("Nome não pode ser vazio");
        }
        if (valor.length() < 3) {
            throw new ValidationException("Nome deve ter no mínimo 3 caracteres");
        }
        if (valor.length() > 255) {
            throw new ValidationException("Nome não pode exceder 255 caracteres");
        }
    }

    public boolean contemPalavra(String palavra) {
        if (palavra == null || palavra.isBlank()) {
            return false;
        }
        return valor.toLowerCase().contains(palavra.toLowerCase());
    }

    public String paraExibicao() {
        return valor.trim();
    }
}
