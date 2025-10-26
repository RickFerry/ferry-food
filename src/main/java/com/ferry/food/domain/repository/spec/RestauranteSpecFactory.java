package com.ferry.food.domain.repository.spec;

import com.ferry.food.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

public class RestauranteSpecFactory {
    private RestauranteSpecFactory() {}

    public static Specification<Restaurante> comFreteGratis() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("taxaFrete"), 0);
    }

    public static Specification<Restaurante> freteGratisNomeSemelhante(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}
