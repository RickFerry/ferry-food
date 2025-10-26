package com.ferry.food.domain.repository.impl;

import com.ferry.food.domain.repository.CustomJpaRepository;
import lombok.var;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {
    private final EntityManager em;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    @Override
    public Optional<T> findFirst() {
        var criteriaBuilder = em.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(getDomainClass());
        var root = criteriaQuery.from(getDomainClass());
        criteriaQuery.select(root);
        var query = em.createQuery(criteriaQuery);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }
}
