package com.ferry.food.application.dtos.output.restaurante;

import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteDTO {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private CidadeDTO cidade;
    private CozinhaDTO cozinha;

    public Long id() {
        return this.id;
    }

    public String nome() {
        return this.nome;
    }
}
