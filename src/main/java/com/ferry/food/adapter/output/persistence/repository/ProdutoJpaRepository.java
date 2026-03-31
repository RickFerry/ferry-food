package com.ferry.food.adapter.output.persistence.repository;

import com.ferry.food.adapter.output.persistence.entity.ProdutoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoJpaRepository extends JpaRepository<ProdutoJpaEntity, Long> {
    List<ProdutoJpaEntity> findByRestauranteId(Long restauranteId);
}
