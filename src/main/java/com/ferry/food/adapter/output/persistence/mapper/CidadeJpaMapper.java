package com.ferry.food.adapter.output.persistence.mapper;

import com.ferry.food.adapter.output.persistence.entity.CidadeJpaEntity;
import com.ferry.food.adapter.output.persistence.entity.EstadoJpaEntity;
import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.entities.Estado;
import org.springframework.stereotype.Component;

@Component
public class CidadeJpaMapper {
    
    public Cidade toDomainEntity(CidadeJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        Estado estado = null;
        if (jpaEntity.estado != null) {
            estado = Estado.criar(jpaEntity.estado.id, jpaEntity.estado.nome);
        }
        return Cidade.criar(jpaEntity.id, jpaEntity.nome, estado);
    }
    
    public CidadeJpaEntity toJpaEntity(Cidade domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        CidadeJpaEntity entity = new CidadeJpaEntity();
        entity.id = domainEntity.getId();
        entity.nome = domainEntity.getNome().getValor();
        entity.estadoId = domainEntity.getEstado() != null ? domainEntity.getEstado().getId() : null;
        if (domainEntity.getEstado() != null) {
            EstadoJpaEntity estadoJpa = new EstadoJpaEntity();
            estadoJpa.id = domainEntity.getEstado().getId();
            estadoJpa.nome = domainEntity.getEstado().getNome().getValor();
            entity.estado = estadoJpa;
        }
        return entity;
    }
}

