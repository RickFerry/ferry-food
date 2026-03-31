package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Nome;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Estado {
    private Long id;
    private Nome nome;

    public static Estado criar(Long id, String nome) {
        return new Estado(id, new Nome(nome));
    }

    public static Estado criarNovo(String nome) {
        return new Estado(null, new Nome(nome));
    }

    public void atualizarNome(String novoNome) {
        this.nome = new Nome(novoNome);
    }
}
