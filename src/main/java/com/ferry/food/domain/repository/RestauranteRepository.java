package com.ferry.food.domain.repository;

import com.ferry.food.domain.model.Restaurante;
import com.ferry.food.domain.repository.impl.RestauranteRepositoryQueries;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
}
