package com.ferry.food.fixtures;

import com.ferry.food.adapter.output.persistence.entity.CidadeJpaEntity;
import com.ferry.food.adapter.output.persistence.entity.EstadoJpaEntity;
import com.ferry.food.application.dtos.input.cidade.CriarCidadeDTO;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.entities.Estado;

/**
 * Factory class for creating test data for Cidade entities.
 * Provides reusable test fixtures following the builder pattern.
 */
public class CidadeFixture {

    /**
     * Creates a valid Cidade domain entity with id, name and estado.
     * @param estado the Estado for the Cidade
     * @return Cidade with id=1L, nome="São Paulo" and provided estado
     */
    public static Cidade umaCidadeValida(Estado estado) {
        return Cidade.criar(1L, "São Paulo", estado);
    }

    /**
     * Creates a valid Cidade domain entity without id (new).
     * @param estado the Estado for the Cidade
     * @return Cidade without id (null), nome="São Paulo" and provided estado
     */
    public static Cidade umaCidadeNova(Estado estado) {
        return Cidade.criarNova("São Paulo", estado);
    }

    /**
     * Creates a Cidade with custom name and estado.
     * @param nome the name for the Cidade
     * @param estado the Estado for the Cidade
     * @return Cidade with given name and estado
     */
    public static Cidade umaCidadeComNomeEEstado(String nome, Estado estado) {
        return Cidade.criarNova(nome, estado);
    }

    /**
     * Creates a Cidade with custom id, name and estado.
     * @param id the id for the Cidade
     * @param nome the name for the Cidade
     * @param estado the Estado for the Cidade
     * @return Cidade with given id, name and estado
     */
    public static Cidade umaCidadeComIdNomeEEstado(Long id, String nome, Estado estado) {
        return Cidade.criar(id, nome, estado);
    }

    /**
     * Creates a valid input DTO for creating a Cidade.
     * @return CriarCidadeDTO with nome="São Paulo" and estadoId=1L
     */
    public static CriarCidadeDTO umInputCriarCidadeValido() {
        CriarCidadeDTO input = new CriarCidadeDTO();
        input.setNome("São Paulo");
        input.setEstadoId(1L);
        return input;
    }

    /**
     * Creates a Criar Cidade input DTO with custom name and estadoId.
     * @param nome the name for the Cidade
     * @param estadoId the id of the Estado
     * @return CriarCidadeDTO with given name and estadoId
     */
    public static CriarCidadeDTO umInputCriarCidadeComNomeEEstadoId(String nome, Long estadoId) {
        CriarCidadeDTO input = new CriarCidadeDTO();
        input.setNome(nome);
        input.setEstadoId(estadoId);
        return input;
    }

    /**
     * Creates a valid Cidade JPA entity with id, name and estado.
     * @param estado the EstadoJpaEntity for the Cidade
     * @return CidadeJpaEntity with id=1L, nome="São Paulo" and provided estado
     */
    public static CidadeJpaEntity umaCidadeJpaEntity(EstadoJpaEntity estado) {
        CidadeJpaEntity entity = new CidadeJpaEntity();
        entity.setId(1L);
        entity.setNome("São Paulo");
        entity.setEstado(estado);
        return entity;
    }

    /**
     * Creates a Cidade JPA entity with custom name and estado.
     * @param nome the name for the Cidade
     * @param estado the EstadoJpaEntity for the Cidade
     * @return CidadeJpaEntity with given name and estado
     */
    public static CidadeJpaEntity umaCidadeJpaEntityComNomeEEstado(String nome, EstadoJpaEntity estado) {
        CidadeJpaEntity entity = new CidadeJpaEntity();
        entity.setId(1L);
        entity.setNome(nome);
        entity.setEstado(estado);
        return entity;
    }

    /**
     * Creates a Cidade JPA entity with custom id, name and estado.
     * @param id the id for the Cidade
     * @param nome the name for the Cidade
     * @param estado the EstadoJpaEntity for the Cidade
     * @return CidadeJpaEntity with given id, name and estado
     */
    public static CidadeJpaEntity umaCidadeJpaEntityComIdNomeEEstado(Long id, String nome, EstadoJpaEntity estado) {
        CidadeJpaEntity entity = new CidadeJpaEntity();
        entity.setId(id);
        entity.setNome(nome);
        entity.setEstado(estado);
        return entity;
    }

    /**
     * Creates a valid Cidade output DTO with id and name.
     * @return CidadeDTO with id=1L and nome="São Paulo"
     */
    public static CidadeDTO umaCidadeDTOValida() {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(1L);
        dto.setNome("São Paulo");
        dto.setEstado(EstadoFixture.umEstadoDTOValido());
        return dto;
    }

    /**
     * Creates a Cidade output DTO with custom name.
     * @param nome the name for the Cidade
     * @return CidadeDTO with given name
     */
    public static CidadeDTO umaCidadeDTOComNome(String nome) {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(1L);
        dto.setNome(nome);
        dto.setEstado(EstadoFixture.umEstadoDTOValido());
        return dto;
    }

    /**
     * Creates a Cidade output DTO with custom id, name and estado.
     * @param id the id for the Cidade
     * @param nome the name for the Cidade
     * @param estadoDTO the EstadoDTO for the Cidade
     * @return CidadeDTO with given id, name and estado
     */
    public static CidadeDTO umaCidadeDTOComIdNomeEEstado(Long id, String nome, Object estadoDTO) {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setEstado(EstadoFixture.umEstadoDTOValido());
        return dto;
    }
}
