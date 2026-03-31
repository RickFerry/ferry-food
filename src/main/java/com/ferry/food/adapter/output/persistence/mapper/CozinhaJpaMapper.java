package com.ferry.food.adapter.output.persistence.mapper;

import com.ferry.food.adapter.output.persistence.entity.CozinhaJpaEntity;
import com.ferry.food.domain.entities.Cozinha;
import org.springframework.stereotype.Component;

@Component
public class CozinhaJpaMapper {
    
    public Cozinha toDomainEntity(CozinhaJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        return Cozinha.criar(jpaEntity.id, jpaEntity.nome);
    }
    
    public CozinhaJpaEntity toJpaEntity(Cozinha domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        CozinhaJpaEntity entity = new CozinhaJpaEntity();
        entity.id = domainEntity.getId();
        entity.nome = domainEntity.getNome().getValor();
        return entity;
    }
}
