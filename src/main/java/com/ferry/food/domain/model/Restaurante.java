package com.ferry.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;


    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Cozinha cozinha;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "restaurante", orphanRemoval = true)
    private Set<Produto> produtos = new LinkedHashSet<>();

    @JsonIgnore
    @ManyToMany
    @ToString.Exclude
    @JoinTable(name = "restaurante_formasPagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "formaPagamento_id"))
    private Set<FormaPagamento> formasPagamento = new LinkedHashSet<>();
}
