package com.ferry.food.domain.ports.input.cidade;

import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface CriarCidadeUseCase {
    CidadeDTO executar(CriarCidadeInput input);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarCidadeInput {
        private String nome;
        private Long estadoId;

        public String nome() {
            return getNome();
        }

        public Long estadoId() {
            return getEstadoId();
        }
    }
}
