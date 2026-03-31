package com.ferry.food.adapter.output.persistence.mapper;

import com.ferry.food.adapter.output.persistence.entity.EstadoJpaEntity;
import com.ferry.food.domain.entities.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoJpaMapper {
    
    public Estado toDomainEntity(EstadoJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        return Estado.criar(jpaEntity.id, jpaEntity.nome);
    }
    
    public EstadoJpaEntity toJpaEntity(Estado domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        EstadoJpaEntity entity = new EstadoJpaEntity();
        entity.id = domainEntity.getId();
        entity.nome = domainEntity.getNome().getValor();
        return entity;
    }
}
