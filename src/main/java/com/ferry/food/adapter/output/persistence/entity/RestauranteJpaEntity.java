package com.ferry.food.adapter.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "restaurante")
public class RestauranteJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nome")
    public String nome;

    @Column(name = "taxa_frete")
    public BigDecimal taxaFrete;

    @Column(name = "data_cadastro", nullable = false)
    public LocalDateTime dataCadastro;

    @Column(name = "data_atualizacao", nullable = false)
    public LocalDateTime dataAtualizacao;

    @Column(name = "cozinha_id")
    public Long cozinhaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", insertable = false, updatable = false)
    public CozinhaJpaEntity cozinha;

    @Column(name = "cidade_id")
    public Long cidadeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", insertable = false, updatable = false)
    public CidadeJpaEntity cidade;

    @Embedded
    public EnderecoEmbeddable endereco;
}
