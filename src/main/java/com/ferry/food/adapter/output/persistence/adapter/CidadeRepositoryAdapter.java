package com.ferry.food.adapter.output.persistence.adapter;

import com.ferry.food.adapter.output.persistence.entity.CidadeJpaEntity;
import com.ferry.food.adapter.output.persistence.mapper.CidadeJpaMapper;
import com.ferry.food.adapter.output.persistence.repository.CidadeJpaRepository;
import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.ports.output.CidadeRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CidadeRepositoryAdapter implements CidadeRepositoryPort {
    private final CidadeJpaRepository jpaRepository;
    private final CidadeJpaMapper mapper;

    public CidadeRepositoryAdapter(CidadeJpaRepository jpaRepository, CidadeJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Cidade salvar(Cidade cidade) {
        CidadeJpaEntity jpaEntity = mapper.toJpaEntity(cidade);
        CidadeJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Cidade> obterPorId(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomainEntity);
    }

    @Override
    public List<Cidade> listarTodas() {
        return jpaRepository.findAll()
            .stream()
            .map(mapper::toDomainEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Cidade> listarPorEstado(Long estadoId) {
        return jpaRepository.findByEstadoId(estadoId)
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
