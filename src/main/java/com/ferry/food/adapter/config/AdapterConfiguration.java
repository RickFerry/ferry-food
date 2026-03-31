package com.ferry.food.adapter.config;

import com.ferry.food.application.mappers.cidade.CidadeInputMapper;
import com.ferry.food.application.mappers.cidade.CidadeOutputMapper;
import com.ferry.food.application.mappers.cozinha.CozinhaInputMapper;
import com.ferry.food.application.mappers.cozinha.CozinhaOutputMapper;
import com.ferry.food.application.mappers.estado.EstadoInputMapper;
import com.ferry.food.application.mappers.estado.EstadoOutputMapper;
import com.ferry.food.application.mappers.restaurante.RestauranteInputMapper;
import com.ferry.food.application.mappers.restaurante.RestauranteOutputMapper;
import com.ferry.food.application.usecases.cidade.AtualizarCidadeImpl;
import com.ferry.food.application.usecases.cidade.CriarCidadeImpl;
import com.ferry.food.application.usecases.cidade.DeletarCidadeImpl;
import com.ferry.food.application.usecases.cidade.ListarCidadesImpl;
import com.ferry.food.application.usecases.cidade.ObterCidadeImpl;
import com.ferry.food.application.usecases.cozinha.AtualizarCozinhaImpl;
import com.ferry.food.application.usecases.cozinha.CriarCozinhaImpl;
import com.ferry.food.application.usecases.cozinha.DeletarCozinhaImpl;
import com.ferry.food.application.usecases.cozinha.ListarCozinhasImpl;
import com.ferry.food.application.usecases.cozinha.ObterCozinhaImpl;
import com.ferry.food.application.usecases.estado.AtualizarEstadoImpl;
import com.ferry.food.application.usecases.estado.CriarEstadoImpl;
import com.ferry.food.application.usecases.estado.DeletarEstadoImpl;
import com.ferry.food.application.usecases.estado.ListarEstadosImpl;
import com.ferry.food.application.usecases.estado.ObterEstadoImpl;
import com.ferry.food.application.usecases.restaurante.AtualizarRestauranteImpl;
import com.ferry.food.application.usecases.restaurante.CriarRestauranteImpl;
import com.ferry.food.application.usecases.restaurante.DeletarRestauranteImpl;
import com.ferry.food.application.usecases.restaurante.ListarRestaurantesImpl;
import com.ferry.food.application.usecases.restaurante.ObterRestauranteImpl;
import com.ferry.food.domain.domainservices.ValidadorRestaurante;
import com.ferry.food.domain.ports.input.cidade.AtualizarCidadeUseCase;
import com.ferry.food.domain.ports.input.cidade.CriarCidadeUseCase;
import com.ferry.food.domain.ports.input.cidade.DeletarCidadeUseCase;
import com.ferry.food.domain.ports.input.cidade.ListarCidadesUseCase;
import com.ferry.food.domain.ports.input.cidade.ObterCidadeUseCase;
import com.ferry.food.domain.ports.input.cozinha.AtualizarCozinhaUseCase;
import com.ferry.food.domain.ports.input.cozinha.CriarCozinhaUseCase;
import com.ferry.food.domain.ports.input.cozinha.DeletarCozinhaUseCase;
import com.ferry.food.domain.ports.input.cozinha.ListarCozinhasUseCase;
import com.ferry.food.domain.ports.input.cozinha.ObterCozinhaUseCase;
import com.ferry.food.domain.ports.input.estado.AtualizarEstadoUseCase;
import com.ferry.food.domain.ports.input.estado.CriarEstadoUseCase;
import com.ferry.food.domain.ports.input.estado.DeletarEstadoUseCase;
import com.ferry.food.domain.ports.input.estado.ListarEstadosUseCase;
import com.ferry.food.domain.ports.input.estado.ObterEstadoUseCase;
import com.ferry.food.domain.ports.input.restaurante.AtualizarRestauranteUseCase;
import com.ferry.food.domain.ports.input.restaurante.CriarRestauranteUseCase;
import com.ferry.food.domain.ports.input.restaurante.DeletarRestauranteUseCase;
import com.ferry.food.domain.ports.input.restaurante.ListarRestaurantesUseCase;
import com.ferry.food.domain.ports.input.restaurante.ObterRestauranteUseCase;
import com.ferry.food.domain.ports.output.CidadeRepositoryPort;
import com.ferry.food.domain.ports.output.CozinhaRepositoryPort;
import com.ferry.food.domain.ports.output.EstadoRepositoryPort;
import com.ferry.food.domain.ports.output.RestauranteRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for all adapter beans.
 * This class configures all use case beans with their dependencies.
 * Note: UseCase implementations are @Component-scoped in the application layer
 * and autowired by Spring when needed. However, we can optionally define them here
 * as @Bean methods to have explicit control over dependencies.
 *
 * For now, we keep the use cases as @Component annotations in their implementation classes
 * to follow Spring's convention. This configuration class serves as documentation and
 * can be used if we need to define custom beans or override default Spring behavior.
 */
@Configuration
public class AdapterConfiguration {

    // ============================================
    // Cozinha UseCase Beans (5)
    // ============================================

    @Bean
    public CriarCozinhaUseCase criarCozinhaUseCase(
        CozinhaRepositoryPort cozinhaRepository,
        CozinhaOutputMapper outputMapper) {
        return new CriarCozinhaImpl(cozinhaRepository, outputMapper);
    }

    @Bean
    public ListarCozinhasUseCase listarCozinhasUseCase(
        CozinhaRepositoryPort cozinhaRepository,
        CozinhaOutputMapper outputMapper) {
        return new ListarCozinhasImpl(cozinhaRepository, outputMapper);
    }

    @Bean
    public ObterCozinhaUseCase obterCozinhaUseCase(
        CozinhaRepositoryPort cozinhaRepository,
        CozinhaOutputMapper outputMapper) {
        return new ObterCozinhaImpl(cozinhaRepository, outputMapper);
    }

    @Bean
    public AtualizarCozinhaUseCase atualizarCozinhaUseCase(
        CozinhaRepositoryPort cozinhaRepository,
        CozinhaOutputMapper outputMapper) {
        return new AtualizarCozinhaImpl(cozinhaRepository, outputMapper);
    }

    @Bean
    public DeletarCozinhaUseCase deletarCozinhaUseCase(
        CozinhaRepositoryPort cozinhaRepository) {
        return new DeletarCozinhaImpl(cozinhaRepository);
    }

    // ============================================
    // Estado UseCase Beans (5)
    // ============================================

    @Bean
    public CriarEstadoUseCase criarEstadoUseCase(
        EstadoRepositoryPort estadoRepository,
        EstadoOutputMapper outputMapper) {
        return new CriarEstadoImpl(estadoRepository, outputMapper);
    }

    @Bean
    public ListarEstadosUseCase listarEstadosUseCase(
        EstadoRepositoryPort estadoRepository,
        EstadoOutputMapper outputMapper) {
        return new ListarEstadosImpl(estadoRepository, outputMapper);
    }

    @Bean
    public ObterEstadoUseCase obterEstadoUseCase(
        EstadoRepositoryPort estadoRepository,
        EstadoOutputMapper outputMapper) {
        return new ObterEstadoImpl(estadoRepository, outputMapper);
    }

    @Bean
    public AtualizarEstadoUseCase atualizarEstadoUseCase(
        EstadoRepositoryPort estadoRepository,
        EstadoOutputMapper outputMapper) {
        return new AtualizarEstadoImpl(estadoRepository, outputMapper);
    }

    @Bean
    public DeletarEstadoUseCase deletarEstadoUseCase(
        EstadoRepositoryPort estadoRepository) {
        return new DeletarEstadoImpl(estadoRepository);
    }

    // ============================================
    // Cidade UseCase Beans (5)
    // ============================================

    @Bean
    public CriarCidadeUseCase criarCidadeUseCase(
        CidadeRepositoryPort cidadeRepository,
        EstadoRepositoryPort estadoRepository,
        CidadeOutputMapper outputMapper) {
        return new CriarCidadeImpl(cidadeRepository, estadoRepository, outputMapper);
    }

    @Bean
    public ListarCidadesUseCase listarCidadesUseCase(
        CidadeRepositoryPort cidadeRepository,
        CidadeOutputMapper outputMapper) {
        return new ListarCidadesImpl(cidadeRepository, outputMapper);
    }

    @Bean
    public ObterCidadeUseCase obterCidadeUseCase(
        CidadeRepositoryPort cidadeRepository,
        CidadeOutputMapper outputMapper) {
        return new ObterCidadeImpl(cidadeRepository, outputMapper);
    }

    @Bean
    public AtualizarCidadeUseCase atualizarCidadeUseCase(
        CidadeRepositoryPort cidadeRepository,
        EstadoRepositoryPort estadoRepository,
        CidadeOutputMapper outputMapper) {
        return new AtualizarCidadeImpl(cidadeRepository, estadoRepository, outputMapper);
    }

    @Bean
    public DeletarCidadeUseCase deletarCidadeUseCase(
        CidadeRepositoryPort cidadeRepository) {
        return new DeletarCidadeImpl(cidadeRepository);
    }

    // ============================================
    // Restaurante UseCase Beans (5)
    // ============================================

    @Bean
    public CriarRestauranteUseCase criarRestauranteUseCase(
        RestauranteRepositoryPort restauranteRepository,
        CozinhaRepositoryPort cozinhaRepository,
        RestauranteOutputMapper outputMapper,
        ValidadorRestaurante validador) {
        return new CriarRestauranteImpl(restauranteRepository, cozinhaRepository, outputMapper, validador);
    }

    @Bean
    public ListarRestaurantesUseCase listarRestaurantesUseCase(
        RestauranteRepositoryPort restauranteRepository,
        RestauranteOutputMapper outputMapper) {
        return new ListarRestaurantesImpl(restauranteRepository, outputMapper);
    }

    @Bean
    public ObterRestauranteUseCase obterRestauranteUseCase(
        RestauranteRepositoryPort restauranteRepository,
        RestauranteOutputMapper outputMapper) {
        return new ObterRestauranteImpl(restauranteRepository, outputMapper);
    }

    @Bean
    public AtualizarRestauranteUseCase atualizarRestauranteUseCase(
        RestauranteRepositoryPort restauranteRepository,
        CozinhaRepositoryPort cozinhaRepository,
        RestauranteOutputMapper outputMapper,
        ValidadorRestaurante validador) {
        return new AtualizarRestauranteImpl(restauranteRepository, cozinhaRepository, outputMapper, validador);
    }

    @Bean
    public DeletarRestauranteUseCase deletarRestauranteUseCase(
        RestauranteRepositoryPort restauranteRepository,
        ValidadorRestaurante validador) {
        return new DeletarRestauranteImpl(restauranteRepository, validador);
    }
}
