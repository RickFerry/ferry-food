package com.ferry.food.adapter.output.persistence.repository;

import com.ferry.food.adapter.output.persistence.entity.PedidoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoJpaEntity, Long> {
    List<PedidoJpaEntity> findByRestauranteId(Long restauranteId);
}
