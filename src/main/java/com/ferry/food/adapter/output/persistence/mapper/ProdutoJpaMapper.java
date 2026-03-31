package com.ferry.food.adapter.output.persistence.mapper;

import com.ferry.food.adapter.output.persistence.entity.ProdutoJpaEntity;
import com.ferry.food.domain.entities.Produto;
import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.entities.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoJpaMapper {
    
    @Autowired
    private RestauranteJpaMapper restauranteMapper;
    
    public Produto toDomainEntity(ProdutoJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        
        Restaurante restaurante = null;
        if (jpaEntity.restaurante != null) {
            restaurante = restauranteMapper.toDomainEntity(jpaEntity.restaurante);
        }
        
        return Produto.criar(
            jpaEntity.id,
            jpaEntity.nome,
            jpaEntity.descricao,
            jpaEntity.preco,
            jpaEntity.ativo != null && jpaEntity.ativo,
            restaurante
        );
    }
    
    public ProdutoJpaEntity toJpaEntity(Produto domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        ProdutoJpaEntity entity = new ProdutoJpaEntity();
        entity.id = domainEntity.getId();
        entity.nome = domainEntity.getNome().getValor();
        entity.descricao = domainEntity.getDescricao();
        entity.preco = domainEntity.getPreco().getValor();
        entity.ativo = domainEntity.isAtivo();
        
        if (domainEntity.getRestaurante() != null) {
            entity.restauranteId = domainEntity.getRestaurante().getId();
            entity.restaurante = restauranteMapper.toJpaEntity(domainEntity.getRestaurante());
        }
        
        return entity;
    }
}
