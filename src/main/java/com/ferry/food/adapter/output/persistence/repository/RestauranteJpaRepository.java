package com.ferry.food.adapter.output.persistence.repository;

import com.ferry.food.adapter.output.persistence.entity.RestauranteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteJpaRepository extends JpaRepository<RestauranteJpaEntity, Long> {
    @Query("SELECT r FROM RestauranteJpaEntity r WHERE r.taxaFrete = 0")
    List<RestauranteJpaEntity> findWithFreeShipping();

    @Query("SELECT r FROM RestauranteJpaEntity r WHERE r.nome LIKE %:nome% AND r.taxaFrete = 0")
    List<RestauranteJpaEntity> findWithFreeShippingContainingName(String nome);

    @Query("SELECT r FROM RestauranteJpaEntity r ORDER BY r.id ASC LIMIT 1")
    RestauranteJpaEntity findFirstRestaurante();
}
