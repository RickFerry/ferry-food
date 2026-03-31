package com.ferry.food.domain.entities;

import com.ferry.food.domain.valueobjects.Endereco;
import com.ferry.food.domain.valueobjects.Nome;
import com.ferry.food.domain.valueobjects.Taxa;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Restaurante {
    private Long id;
    private Nome nome;
    private Taxa taxaFrete;
    private Endereco endereco;
    private Cozinha cozinha;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
    private Set<FormaPagamento> formasPagamento;

    public static Restaurante criar(
        Long id,
        String nome,
        java.math.BigDecimal taxaFrete,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Long cidadeId,
        Cozinha cozinha,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao
    ) {
        return new Restaurante(
            id,
            new Nome(nome),
            new Taxa(taxaFrete),
            new Endereco(logradouro, numero, complemento, bairro, cep, cidadeId),
            cozinha,
            dataCadastro,
            dataAtualizacao,
            new HashSet<>()
        );
    }

    public static Restaurante criarNovo(
        String nome,
        java.math.BigDecimal taxaFrete,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Long cidadeId,
        Cozinha cozinha
    ) {
        LocalDateTime agora = LocalDateTime.now();
        return new Restaurante(
            null,
            new Nome(nome),
            new Taxa(taxaFrete),
            new Endereco(logradouro, numero, complemento, bairro, cep, cidadeId),
            cozinha,
            agora,
            agora,
            new HashSet<>()
        );
    }

    public void atualizarDados(
        String nome,
        java.math.BigDecimal taxaFrete,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cep,
        Long cidadeId
    ) {
        this.nome = new Nome(nome);
        this.taxaFrete = new Taxa(taxaFrete);
        this.endereco = new Endereco(logradouro, numero, complemento, bairro, cep, cidadeId);
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void atualizarCozinha(Cozinha novaCozinha) {
        if (novaCozinha == null) {
            throw new IllegalArgumentException("Cozinha não pode ser nula");
        }
        this.cozinha = novaCozinha;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void adicionarFormaPagamento(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento não pode ser nula");
        }
        this.formasPagamento.add(formaPagamento);
    }

    public void removerFormaPagamento(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new IllegalArgumentException("Forma de pagamento não pode ser nula");
        }
        this.formasPagamento.remove(formaPagamento);
    }

    public boolean temFreteGratis() {
        return taxaFrete.ehIgualA(new Taxa(java.math.BigDecimal.ZERO));
    }

    public boolean contemFreteGratuitoOuNao() {
        return true;
    }
}
