package com.ferry.food.application.dtos.output.cidade;

import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {
    private Long id;
    private String nome;
    private EstadoDTO estado;

    public Long id() {
        return this.id;
    }

    public String nome() {
        return this.nome;
    }
}
