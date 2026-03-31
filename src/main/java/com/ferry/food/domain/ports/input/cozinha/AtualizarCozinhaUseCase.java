package com.ferry.food.domain.ports.input.cozinha;

import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface AtualizarCozinhaUseCase {
    CozinhaDTO executar(Long id, AtualizarCozinhaInput input);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AtualizarCozinhaInput {
        private String nome;

        public String nome() {
            return getNome();
        }
    }
}
