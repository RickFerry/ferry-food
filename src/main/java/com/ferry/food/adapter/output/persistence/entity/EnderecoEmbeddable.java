package com.ferry.food.adapter.output.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EnderecoEmbeddable {
    @Column(name = "logradouro")
    public String logradouro;

    @Column(name = "numero")
    public String numero;

    @Column(name = "complemento")
    public String complemento;

    @Column(name = "bairro")
    public String bairro;

    @Column(name = "cep")
    public String cep;

    @Column(name = "cidade_id")
    public Long cidadeId;
}
