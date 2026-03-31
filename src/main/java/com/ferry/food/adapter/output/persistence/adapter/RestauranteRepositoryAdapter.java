package com.ferry.food.adapter.output.persistence.adapter;

import com.ferry.food.adapter.output.persistence.entity.RestauranteJpaEntity;
import com.ferry.food.adapter.output.persistence.mapper.RestauranteJpaMapper;
import com.ferry.food.adapter.output.persistence.repository.RestauranteJpaRepository;
import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RestauranteRepositoryAdapter implements RestauranteRepositoryPort {
    private final RestauranteJpaRepository jpaRepository;
    private final RestauranteJpaMapper mapper;

    public RestauranteRepositoryAdapter(RestauranteJpaRepository jpaRepository, RestauranteJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Restaurante salvar(Restaurante restaurante) {
        RestauranteJpaEntity jpaEntity = mapper.toJpaEntity(restaurante);
        RestauranteJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Restaurante> obterPorId(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomainEntity);
    }

    @Override
    public List<Restaurante> listarTodos() {
        return jpaRepository.findAll()
            .stream()
            .map(mapper::toDomainEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Restaurante> listarComFreteGratis() {
        return jpaRepository.findWithFreeShipping()
            .stream()
            .map(mapper::toDomainEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Restaurante> listarComFreteGratis(String nomeContendo) {
        return jpaRepository.findWithFreeShippingContainingName(nomeContendo)
            .stream()
            .map(mapper::toDomainEntity)
            .collect(Collectors.toList());
    }

    @Override
    public Restaurante obterPrimeiro() {
        return jpaRepository.findFirstRestaurante()
            .map(mapper::toDomainEntity)
            .orElse(null);
    }

    @Override
    public void deletar(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return jpaRepository.existsById(id);
    }
}
