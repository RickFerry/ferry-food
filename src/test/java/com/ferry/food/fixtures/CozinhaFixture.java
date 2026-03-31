package com.ferry.food.fixtures;

import com.ferry.food.adapter.output.persistence.entity.CozinhaJpaEntity;
import com.ferry.food.application.dtos.input.cozinha.CriarCozinhaDTO;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import com.ferry.food.domain.entities.Cozinha;

/**
 * Factory class for creating test data for Cozinha entities.
 * Provides reusable test fixtures following the builder pattern.
 */
public class CozinhaFixture {

    /**
     * Creates a valid Cozinha domain entity with id and name.
     * @return Cozinha with id=1L and nome="Italiana"
     */
    public static Cozinha umaCozinhaValida() {
        return Cozinha.criar(1L, "Italiana");
    }

    /**
     * Creates a valid Cozinha domain entity without id (new).
     * @return Cozinha without id (null) and nome="Italiana"
     */
    public static Cozinha umaCozinhaNova() {
        return Cozinha.criarNova("Italiana");
    }

    /**
     * Creates a Cozinha with custom name.
     * @param nome the name for the Cozinha
     * @return Cozinha with given name
     */
    public static Cozinha umaCozinhaComNome(String nome) {
        return Cozinha.criarNova(nome);
    }

    /**
     * Creates a Cozinha with custom id and name.
     * @param id the id for the Cozinha
     * @param nome the name for the Cozinha
     * @return Cozinha with given id and name
     */
    public static Cozinha umaCozinhaComIdENome(Long id, String nome) {
        return Cozinha.criar(id, nome);
    }

    /**
     * Creates a valid input DTO for creating a Cozinha.
     * @return CriarCozinhaDTO with nome="Italiana"
     */
    public static CriarCozinhaDTO umInputCriarCozinhaValido() {
        CriarCozinhaDTO input = new CriarCozinhaDTO();
        input.setNome("Italiana");
        return input;
    }

    /**
     * Creates a Criar Cozinha input DTO with custom name.
     * @param nome the name for the Cozinha
     * @return CriarCozinhaDTO with given name
     */
    public static CriarCozinhaDTO umInputCriarCozinhaComNome(String nome) {
        CriarCozinhaDTO input = new CriarCozinhaDTO();
        input.setNome(nome);
        return input;
    }

    /**
     * Creates a valid Cozinha JPA entity with id and name.
     * @return CozinhaJpaEntity with id=1L and nome="Italiana"
     */
    public static CozinhaJpaEntity umaCozinhaJpaEntity() {
        CozinhaJpaEntity entity = new CozinhaJpaEntity();
        entity.setId(1L);
        entity.setNome("Italiana");
        return entity;
    }

    /**
     * Creates a Cozinha JPA entity with custom name.
     * @param nome the name for the Cozinha
     * @return CozinhaJpaEntity with given name
     */
    public static CozinhaJpaEntity umaCozinhaJpaEntityComNome(String nome) {
        CozinhaJpaEntity entity = new CozinhaJpaEntity();
        entity.setId(1L);
        entity.setNome(nome);
        return entity;
    }

    /**
     * Creates a Cozinha JPA entity with custom id and name.
     * @param id the id for the Cozinha
     * @param nome the name for the Cozinha
     * @return CozinhaJpaEntity with given id and name
     */
    public static CozinhaJpaEntity umaCozinhaJpaEntityComIdENome(Long id, String nome) {
        CozinhaJpaEntity entity = new CozinhaJpaEntity();
        entity.setId(id);
        entity.setNome(nome);
        return entity;
    }

    /**
     * Creates a valid Cozinha output DTO with id and name.
     * @return CozinhaDTO with id=1L and nome="Italiana"
     */
    public static CozinhaDTO umaCozinhaDTOValida() {
        return new CozinhaDTO(1L, "Italiana");
    }

    /**
     * Creates a Cozinha output DTO with custom name.
     * @param nome the name for the Cozinha
     * @return CozinhaDTO with given name
     */
    public static CozinhaDTO umaCozinhaDTOComNome(String nome) {
        return new CozinhaDTO(1L, nome);
    }

    /**
     * Creates a Cozinha output DTO with custom id and name.
     * @param id the id for the Cozinha
     * @param nome the name for the Cozinha
     * @return CozinhaDTO with given id and name
     */
    public static CozinhaDTO umaCozinhaDTOComIdENome(Long id, String nome) {
        return new CozinhaDTO(id, nome);
    }
}
