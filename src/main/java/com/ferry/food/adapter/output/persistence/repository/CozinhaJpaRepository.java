package com.ferry.food.adapter.output.persistence.repository;

import com.ferry.food.adapter.output.persistence.entity.CozinhaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaJpaRepository extends JpaRepository<CozinhaJpaEntity, Long> {
}
