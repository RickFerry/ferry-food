package com.ferry.food.adapter.output.persistence.repository;

import com.ferry.food.adapter.output.persistence.entity.CidadeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeJpaRepository extends JpaRepository<CidadeJpaEntity, Long> {
    List<CidadeJpaEntity> findByEstadoId(Long estadoId);
}
