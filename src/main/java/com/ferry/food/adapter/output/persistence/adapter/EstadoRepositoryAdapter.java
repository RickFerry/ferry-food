package com.ferry.food.adapter.output.persistence.adapter;

import com.ferry.food.adapter.output.persistence.entity.EstadoJpaEntity;
import com.ferry.food.adapter.output.persistence.mapper.EstadoJpaMapper;
import com.ferry.food.adapter.output.persistence.repository.EstadoJpaRepository;
import com.ferry.food.domain.entities.Estado;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EstadoRepositoryAdapter implements EstadoRepositoryPort {
    private final EstadoJpaRepository jpaRepository;
    private final EstadoJpaMapper mapper;

    public EstadoRepositoryAdapter(EstadoJpaRepository jpaRepository, EstadoJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Estado salvar(Estado estado) {
        EstadoJpaEntity jpaEntity = mapper.toJpaEntity(estado);
        EstadoJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Estado> obterPorId(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomainEntity);
    }

    @Override
    public List<Estado> listarTodos() {
        return jpaRepository.findAll()
            .stream()
            .map(mapper::toDomainEntity)
            .collect(Collectors.toList());
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
