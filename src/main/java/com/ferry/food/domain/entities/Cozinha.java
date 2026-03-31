package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Nome;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cozinha {
    private Long id;
    private Nome nome;

    public static Cozinha criar(Long id, String nome) {
        return new Cozinha(id, new Nome(nome));
    }

    public static Cozinha criarNova(String nome) {
        return new Cozinha(null, new Nome(nome));
    }

    public void atualizarNome(String novoNome) {
        this.nome = new Nome(novoNome);
    }
}
