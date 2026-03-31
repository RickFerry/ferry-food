package com.ferry.food.adapter.output.persistence.mapper;

import com.ferry.food.adapter.output.persistence.entity.RestauranteJpaEntity;
import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.valueobjects.Endereco;
import org.springframework.stereotype.Component;

@Component
public class RestauranteJpaMapper {
    
    public Restaurante toDomainEntity(RestauranteJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        
        Cozinha cozinha = null;
        if (jpaEntity.cozinha != null) {
            cozinha = Cozinha.criar(jpaEntity.cozinha.id, jpaEntity.cozinha.nome);
        }
        
        return Restaurante.criar(
            jpaEntity.id,
            jpaEntity.nome,
            jpaEntity.taxaFrete,
            jpaEntity.endereco.logradouro,
            jpaEntity.endereco.numero,
            jpaEntity.endereco.complemento,
            jpaEntity.endereco.bairro,
            jpaEntity.endereco.cep,
            jpaEntity.cidadeId,
            cozinha,
            jpaEntity.dataCadastro,
            jpaEntity.dataAtualizacao
        );
    }
    
    public RestauranteJpaEntity toJpaEntity(Restaurante domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        RestauranteJpaEntity entity = new RestauranteJpaEntity();
        entity.id = domainEntity.getId();
        entity.nome = domainEntity.getNome().getValor();
        entity.taxaFrete = domainEntity.getTaxaFrete().getValor();
        entity.dataCadastro = domainEntity.getDataCadastro();
        entity.dataAtualizacao = domainEntity.getDataAtualizacao();
        
        if (domainEntity.getCozinha() != null) {
            entity.cozinhaId = domainEntity.getCozinha().getId();
        }
        
        if (domainEntity.getEndereco() != null) {
            entity.cidadeId = domainEntity.getEndereco().getCidadeId();
            entity.endereco = new com.ferry.food.adapter.output.persistence.entity.EnderecoEmbeddable();
            entity.endereco.logradouro = domainEntity.getEndereco().getLogradouro();
            entity.endereco.numero = domainEntity.getEndereco().getNumero();
            entity.endereco.complemento = domainEntity.getEndereco().getComplemento();
            entity.endereco.bairro = domainEntity.getEndereco().getBairro();
            entity.endereco.cep = domainEntity.getEndereco().getCep();
        }
        
        return entity;
    }
}
