package com.ferry.food.application.dtos.output.estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoDTO {
    private Long id;
    private String nome;

    public Long id() {
        return this.id;
    }

    public String nome() {
        return this.nome;
    }
}
