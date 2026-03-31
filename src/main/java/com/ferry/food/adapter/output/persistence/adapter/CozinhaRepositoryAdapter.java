package com.ferry.food.adapter.output.persistence.adapter;

import com.ferry.food.adapter.output.persistence.entity.CozinhaJpaEntity;
import com.ferry.food.adapter.output.persistence.mapper.CozinhaJpaMapper;
import com.ferry.food.adapter.output.persistence.repository.CozinhaJpaRepository;
import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CozinhaRepositoryAdapter implements CozinhaRepositoryPort {
    private final CozinhaJpaRepository jpaRepository;
    private final CozinhaJpaMapper mapper;

    public CozinhaRepositoryAdapter(CozinhaJpaRepository jpaRepository, CozinhaJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Cozinha salvar(Cozinha cozinha) {
        CozinhaJpaEntity jpaEntity = mapper.toJpaEntity(cozinha);
        CozinhaJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Cozinha> obterPorId(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomainEntity);
    }

    @Override
    public List<Cozinha> listarTodas() {
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
