package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Nome;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cidade {
    private Long id;
    private Nome nome;
    private Estado estado;

    public static Cidade criar(Long id, String nome, Estado estado) {
        return new Cidade(id, new Nome(nome), estado);
    }

    public static Cidade criarNova(String nome, Estado estado) {
        return new Cidade(null, new Nome(nome), estado);
    }

    public void atualizarNome(String novoNome) {
        this.nome = new Nome(novoNome);
    }

    public void atualizarEstado(Estado novoEstado) {
        if (novoEstado == null) {
            throw new IllegalArgumentException("Estado não pode ser nulo");
        }
        this.estado = novoEstado;
    }
}
