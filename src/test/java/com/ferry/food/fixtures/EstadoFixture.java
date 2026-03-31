package com.ferry.food.fixtures;

import com.ferry.food.adapter.output.persistence.entity.EstadoJpaEntity;
import com.ferry.food.application.dtos.input.estado.CriarEstadoDTO;
import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import com.ferry.food.domain.entities.Estado;

/**
 * Factory class for creating test data for Estado entities.
 * Provides reusable test fixtures following the builder pattern.
 */
public class EstadoFixture {

    /**
     * Creates a valid Estado domain entity with id and name.
     * @return Estado with id=1L and nome="São Paulo"
     */
    public static Estado umEstadoValido() {
        return Estado.criar(1L, "São Paulo");
    }

    /**
     * Creates a valid Estado domain entity without id (new).
     * @return Estado without id (null) and nome="São Paulo"
     */
    public static Estado umEstadoNovo() {
        return Estado.criarNovo("São Paulo");
    }

    /**
     * Creates an Estado with custom name.
     * @param nome the name for the Estado
     * @return Estado with given name
     */
    public static Estado umEstadoComNome(String nome) {
        return Estado.criarNovo(nome);
    }

    /**
     * Creates an Estado with custom id and name.
     * @param id the id for the Estado
     * @param nome the name for the Estado
     * @return Estado with given id and name
     */
    public static Estado umEstadoComIdENome(Long id, String nome) {
        return Estado.criar(id, nome);
    }

    /**
     * Creates a valid input DTO for creating an Estado.
     * @return CriarEstadoDTO with nome="São Paulo"
     */
    public static CriarEstadoDTO umInputCriarEstadoValido() {
        CriarEstadoDTO input = new CriarEstadoDTO();
        input.setNome("São Paulo");
        return input;
    }

    /**
     * Creates a Criar Estado input DTO with custom name.
     * @param nome the name for the Estado
     * @return CriarEstadoDTO with given name
     */
    public static CriarEstadoDTO umInputCriarEstadoComNome(String nome) {
        CriarEstadoDTO input = new CriarEstadoDTO();
        input.setNome(nome);
        return input;
    }

    /**
     * Creates a valid Estado JPA entity with id and name.
     * @return EstadoJpaEntity with id=1L and nome="São Paulo"
     */
    public static EstadoJpaEntity umEstadoJpaEntity() {
        EstadoJpaEntity entity = new EstadoJpaEntity();
        entity.setId(1L);
        entity.setNome("São Paulo");
        return entity;
    }

    /**
     * Creates an Estado JPA entity with custom name.
     * @param nome the name for the Estado
     * @return EstadoJpaEntity with given name
     */
    public static EstadoJpaEntity umEstadoJpaEntityComNome(String nome) {
        EstadoJpaEntity entity = new EstadoJpaEntity();
        entity.setId(1L);
        entity.setNome(nome);
        return entity;
    }

    /**
     * Creates an Estado JPA entity with custom id and name.
     * @param id the id for the Estado
     * @param nome the name for the Estado
     * @return EstadoJpaEntity with given id and name
     */
    public static EstadoJpaEntity umEstadoJpaEntityComIdENome(Long id, String nome) {
        EstadoJpaEntity entity = new EstadoJpaEntity();
        entity.setId(id);
        entity.setNome(nome);
        return entity;
    }

    /**
     * Creates a valid Estado output DTO with id and name.
     * @return EstadoDTO with id=1L and nome="São Paulo"
     */
    public static EstadoDTO umEstadoDTOValido() {
        return new EstadoDTO(1L, "São Paulo");
    }

    /**
     * Creates an Estado output DTO with custom name.
     * @param nome the name for the Estado
     * @return EstadoDTO with given name
     */
    public static EstadoDTO umEstadoDTOComNome(String nome) {
        return new EstadoDTO(1L, nome);
    }

    /**
     * Creates an Estado output DTO with custom id and name.
     * @param id the id for the Estado
     * @param nome the name for the Estado
     * @return EstadoDTO with given id and name
     */
    public static EstadoDTO umEstadoDTOComIdENome(Long id, String nome) {
        return new EstadoDTO(id, nome);
    }
}
