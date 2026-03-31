package com.ferry.food.domain.ports.input.restaurante;

import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

public interface CriarRestauranteUseCase {
    RestauranteDTO executar(CriarRestauranteInput input);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class CriarRestauranteInput {
        private String nome;
        private BigDecimal taxaFrete;
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cep;
        private Long cidadeId;
        private Long cozinhaId;

        public String nome() {
            return getNome();
        }

        public BigDecimal taxaFrete() {
            return getTaxaFrete();
        }

        public String logradouro() {
            return getLogradouro();
        }

        public String numero() {
            return getNumero();
        }

        public String complemento() {
            return getComplemento();
        }

        public String bairro() {
            return getBairro();
        }

        public String cep() {
            return getCep();
        }

        public Long cidadeId() {
            return getCidadeId();
        }

        public Long cozinhaId() {
            return getCozinhaId();
        }
    }
}
