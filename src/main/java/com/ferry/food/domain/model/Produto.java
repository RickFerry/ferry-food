package com.ferry.food.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean ativo;
    private BigDecimal preco;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}
