package com.ferry.food.adapter.output.persistence.adapter;

import com.ferry.food.adapter.output.persistence.entity.ProdutoJpaEntity;
import com.ferry.food.adapter.output.persistence.mapper.ProdutoJpaMapper;
import com.ferry.food.adapter.output.persistence.repository.ProdutoJpaRepository;
import com.ferry.food.domain.entities.Produto;
import com.ferry.food.domain.ports.output.ProdutoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPort {
    private final ProdutoJpaRepository jpaRepository;
    private final ProdutoJpaMapper mapper;

    public ProdutoRepositoryAdapter(ProdutoJpaRepository jpaRepository, ProdutoJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoJpaEntity jpaEntity = mapper.toJpaEntity(produto);
        ProdutoJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Produto> obterPorId(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomainEntity);
    }

    @Override
    public List<Produto> listarTodos() {
        return jpaRepository.findAll()
            .stream()
            .map(mapper::toDomainEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Produto> listarPorRestaurante(Long restauranteId) {
        return jpaRepository.findByRestauranteId(restauranteId)
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
