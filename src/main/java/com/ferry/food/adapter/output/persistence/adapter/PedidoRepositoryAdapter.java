package com.ferry.food.adapter.output.persistence.adapter;

import com.ferry.food.adapter.output.persistence.entity.PedidoJpaEntity;
import com.ferry.food.adapter.output.persistence.mapper.PedidoJpaMapper;
import com.ferry.food.adapter.output.persistence.repository.PedidoJpaRepository;
import com.ferry.food.domain.entities.Pedido;
import com.ferry.food.domain.ports.output.PedidoRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {
    private final PedidoJpaRepository jpaRepository;
    private final PedidoJpaMapper mapper;

    public PedidoRepositoryAdapter(PedidoJpaRepository jpaRepository, PedidoJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoJpaEntity jpaEntity = mapper.toJpaEntity(pedido);
        PedidoJpaEntity saved = jpaRepository.save(jpaEntity);
        return mapper.toDomainEntity(saved);
    }

    @Override
    public Optional<Pedido> obterPorId(Long id) {
        return jpaRepository.findById(id)
            .map(mapper::toDomainEntity);
    }

    @Override
    public List<Pedido> listarTodos() {
        return jpaRepository.findAll()
            .stream()
            .map(mapper::toDomainEntity)
            .collect(Collectors.toList());
    }

    @Override
    public List<Pedido> listarPorRestaurante(Long restauranteId) {
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
