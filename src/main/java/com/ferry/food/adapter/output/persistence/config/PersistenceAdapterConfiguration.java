package com.ferry.food.adapter.output.persistence.config;

import com.ferry.food.adapter.output.persistence.adapter.CidadeRepositoryAdapter;
import com.ferry.food.adapter.output.persistence.adapter.CozinhaRepositoryAdapter;
import com.ferry.food.adapter.output.persistence.adapter.EstadoRepositoryAdapter;
import com.ferry.food.adapter.output.persistence.adapter.PedidoRepositoryAdapter;
import com.ferry.food.adapter.output.persistence.adapter.ProdutoRepositoryAdapter;
import com.ferry.food.adapter.output.persistence.adapter.RestauranteRepositoryAdapter;
import com.ferry.food.adapter.output.persistence.mapper.CidadeJpaMapper;
import com.ferry.food.adapter.output.persistence.mapper.CozinhaJpaMapper;
import com.ferry.food.adapter.output.persistence.mapper.EstadoJpaMapper;
import com.ferry.food.adapter.output.persistence.mapper.PedidoJpaMapper;
import com.ferry.food.adapter.output.persistence.mapper.ProdutoJpaMapper;
import com.ferry.food.adapter.output.persistence.mapper.RestauranteJpaMapper;
import com.ferry.food.adapter.output.persistence.repository.CidadeJpaRepository;
import com.ferry.food.adapter.output.persistence.repository.CozinhaJpaRepository;
import com.ferry.food.adapter.output.persistence.repository.EstadoJpaRepository;
import com.ferry.food.adapter.output.persistence.repository.PedidoJpaRepository;
import com.ferry.food.adapter.output.persistence.repository.ProdutoJpaRepository;
import com.ferry.food.adapter.output.persistence.repository.RestauranteJpaRepository;
import com.ferry.food.domain.ports.output.CidadeRepositoryPort;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import com.ferry.food.domain.ports.output.PedidoRepositoryPort;
import com.ferry.food.domain.ports.output.ProdutoRepositoryPort;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for persistence layer adapters.
 * This class configures all repository adapter beans that implement the repository ports.
 * Each adapter acts as a bridge between the domain layer (ports) and the infrastructure layer (JPA repositories).
 *
 * The adapters are responsible for:
 * - Converting domain entities to JPA entities (and vice versa)
 * - Delegating database operations to JPA repositories
 * - Providing a clean API to the application layer
 */
@Configuration
public class PersistenceAdapterConfiguration {

    // ============================================
    // Cozinha Repository Adapter
    // ============================================

    @Bean
    public CozinhaRepositoryPort cozinhaRepositoryPort(
        CozinhaJpaRepository jpaRepository,
        CozinhaJpaMapper mapper) {
        return new CozinhaRepositoryAdapter(jpaRepository, mapper);
    }

    // ============================================
    // Estado Repository Adapter
    // ============================================

    @Bean
    public EstadoRepositoryPort estadoRepositoryPort(
        EstadoJpaRepository jpaRepository,
        EstadoJpaMapper mapper) {
        return new EstadoRepositoryAdapter(jpaRepository, mapper);
    }

    // ============================================
    // Cidade Repository Adapter
    // ============================================

    @Bean
    public CidadeRepositoryPort cidadeRepositoryPort(
        CidadeJpaRepository jpaRepository,
        CidadeJpaMapper mapper) {
        return new CidadeRepositoryAdapter(jpaRepository, mapper);
    }

    // ============================================
    // Restaurante Repository Adapter
    // ============================================

    @Bean
    public RestauranteRepositoryPort restauranteRepositoryPort(
        RestauranteJpaRepository jpaRepository,
        RestauranteJpaMapper mapper) {
        return new RestauranteRepositoryAdapter(jpaRepository, mapper);
    }

    // ============================================
    // Pedido Repository Adapter
    // ============================================

    @Bean
    public PedidoRepositoryPort pedidoRepositoryPort(
        PedidoJpaRepository jpaRepository,
        PedidoJpaMapper mapper) {
        return new PedidoRepositoryAdapter(jpaRepository, mapper);
    }

    // ============================================
    // Produto Repository Adapter
    // ============================================

    @Bean
    public ProdutoRepositoryPort produtoRepositoryPort(
        ProdutoJpaRepository jpaRepository,
        ProdutoJpaMapper mapper) {
        return new ProdutoRepositoryAdapter(jpaRepository, mapper);
    }
}
