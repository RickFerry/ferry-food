package com.ferry.food.domain.ports.input.estado;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface AtualizarEstadoUseCase {
    EstadoDTO executar(Long id, AtualizarEstadoInput input);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AtualizarEstadoInput {
        private String nome;

        public String nome() {
            return getNome();
        }
    }
}
