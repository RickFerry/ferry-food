package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Nome;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FormaPagamento {
    private Long id;
    private Nome descricao;

    public static FormaPagamento criar(Long id, String descricao) {
        return new FormaPagamento(id, new Nome(descricao));
    }

    public static FormaPagamento criarNova(String descricao) {
        return new FormaPagamento(null, new Nome(descricao));
    }

    public void atualizarDescricao(String novaDescricao) {
        this.descricao = new Nome(novaDescricao);
    }
}
