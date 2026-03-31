package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Nome;
import com.ferry.food.domain.valueobjects.Taxa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Produto {
    private Long id;
    private Nome nome;
    private String descricao;
    private Taxa preco;
    private boolean ativo;
    private Restaurante restaurante;

    public static Produto criar(
        Long id,
        String nome,
        String descricao,
        java.math.BigDecimal preco,
        boolean ativo,
        Restaurante restaurante
    ) {
        return new Produto(
            id,
            new Nome(nome),
            descricao,
            new Taxa(preco),
            ativo,
            restaurante
        );
    }

    public static Produto criarNovo(
        String nome,
        String descricao,
        java.math.BigDecimal preco,
        Restaurante restaurante
    ) {
        return new Produto(
            null,
            new Nome(nome),
            descricao,
            new Taxa(preco),
            true,
            restaurante
        );
    }

    public void atualizarDados(String nome, String descricao, java.math.BigDecimal preco) {
        this.nome = new Nome(nome);
        this.descricao = descricao;
        this.preco = new Taxa(preco);
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
