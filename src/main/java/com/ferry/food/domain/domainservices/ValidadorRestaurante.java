package com.ferry.food.domain.domainservices;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRestaurante {

    public void validarParaCriacao(String nome, Cozinha cozinhaRecuperada) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new BusinessException("Nome do restaurante é obrigatório");
        }

        if (cozinhaRecuperada == null) {
            throw new BusinessException("Cozinha do restaurante é obrigatória");
        }
    }

    public void validarParaAtualizacao(Restaurante restaurante) {
        if (restaurante == null) {
            throw new BusinessException("Restaurante não pode ser nulo");
        }

        if (restaurante.getNome() == null) {
            throw new BusinessException("Nome do restaurante é obrigatório");
        }

        if (restaurante.getCozinha() == null) {
            throw new BusinessException("Cozinha do restaurante é obrigatória");
        }

        if (restaurante.getEndereco() == null) {
            throw new BusinessException("Endereço do restaurante é obrigatório");
        }
    }

    public void validarParaDeleção(Restaurante restaurante) {
        if (restaurante == null) {
            throw new BusinessException("Restaurante não pode ser nulo para deleção");
        }
    }
}
