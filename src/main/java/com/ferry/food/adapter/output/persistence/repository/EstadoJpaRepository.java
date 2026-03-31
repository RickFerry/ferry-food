package com.ferry.food.adapter.output.persistence.repository;

import com.ferry.food.adapter.output.persistence.entity.EstadoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoJpaRepository extends JpaRepository<EstadoJpaEntity, Long> {
}
