package com.ferry.food.fixtures;

import com.ferry.food.adapter.output.persistence.entity.CozinhaJpaEntity;
import com.ferry.food.adapter.output.persistence.entity.EnderecoEmbeddable;
import com.ferry.food.adapter.output.persistence.entity.RestauranteJpaEntity;
import com.ferry.food.application.dtos.input.restaurante.CriarRestauranteDTO;
import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.entities.Restaurante;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Factory class for creating test data for Restaurante entities.
 * Provides reusable test fixtures following the builder pattern.
 */
public class RestauranteFixture {

    /**
     * Creates a valid Restaurante domain entity with cozinha and endereco.
     * @param cozinha the Cozinha for the Restaurante
     * @return Restaurante with all required data
     */
    public static Restaurante umRestauranteValido(Cozinha cozinha) {
        LocalDateTime agora = LocalDateTime.now();
        return Restaurante.criar(
            1L,
            "Pizzaria Italia",
            new BigDecimal("15.50"),
            "Rua das Flores",
            "123",
            "Apto 456",
            "Centro",
            "01310-100",
            1L,
            cozinha,
            agora,
            agora
        );
    }

    /**
     * Creates a valid Restaurante domain entity without id (new).
     * @param cozinha the Cozinha for the Restaurante
     * @return new Restaurante without id
     */
    public static Restaurante umRestauranteNovo(Cozinha cozinha) {
        return Restaurante.criarNovo(
            "Pizzaria Italia",
            new BigDecimal("15.50"),
            "Rua das Flores",
            "123",
            "Apto 456",
            "Centro",
            "01310-100",
            1L,
            cozinha
        );
    }

    /**
     * Creates a Restaurante with custom name and cozinha.
     * @param nome the name for the Restaurante
     * @param cozinha the Cozinha for the Restaurante
     * @return Restaurante with given name and cozinha
     */
    public static Restaurante umRestauranteComNomeECozinha(String nome, Cozinha cozinha) {
        return Restaurante.criarNovo(
            nome,
            new BigDecimal("15.50"),
            "Rua das Flores",
            "123",
            "Apto 456",
            "Centro",
            "01310-100",
            1L,
            cozinha
        );
    }

    /**
     * Creates a Restaurante with custom taxa frete.
     * @param taxaFrete the taxa frete for the Restaurante
     * @param cozinha the Cozinha for the Restaurante
     * @return Restaurante with given taxa frete and cozinha
     */
    public static Restaurante umRestauranteComTaxaFrete(BigDecimal taxaFrete, Cozinha cozinha) {
        return Restaurante.criarNovo(
            "Pizzaria Italia",
            taxaFrete,
            "Rua das Flores",
            "123",
            "Apto 456",
            "Centro",
            "01310-100",
            1L,
            cozinha
        );
    }

    /**
     * Creates a valid input DTO for creating a Restaurante.
     * @return CriarRestauranteDTO with all required data
     */
    public static CriarRestauranteDTO umInputCriarRestauranteValido() {
        CriarRestauranteDTO input = new CriarRestauranteDTO();
        input.setNome("Pizzaria Italia");
        input.setTaxaFrete(new BigDecimal("15.50"));
        input.setLogradouro("Rua das Flores");
        input.setNumero("123");
        input.setComplemento("Apto 456");
        input.setBairro("Centro");
        input.setCep("01310-100");
        input.setCidadeId(1L);
        input.setCozinhaId(1L);
        return input;
    }

    /**
     * Creates a Criar Restaurante input DTO with custom nome and cozinha.
     * @param nome the name for the Restaurante
     * @param cozinhaId the id of the Cozinha
     * @return CriarRestauranteDTO with given name and cozinha id
     */
    public static CriarRestauranteDTO umInputCriarRestauranteComNomeECozinha(String nome, Long cozinhaId) {
        CriarRestauranteDTO input = new CriarRestauranteDTO();
        input.setNome(nome);
        input.setTaxaFrete(new BigDecimal("15.50"));
        input.setLogradouro("Rua das Flores");
        input.setNumero("123");
        input.setComplemento("Apto 456");
        input.setBairro("Centro");
        input.setCep("01310-100");
        input.setCidadeId(1L);
        input.setCozinhaId(cozinhaId);
        return input;
    }

    /**
     * Creates a valid Restaurante JPA entity with cozinha.
     * @param cozinha the CozinhaJpaEntity for the Restaurante
     * @return RestauranteJpaEntity with all required data
     */
    public static RestauranteJpaEntity umRestauranteJpaEntity(CozinhaJpaEntity cozinha) {
        RestauranteJpaEntity entity = new RestauranteJpaEntity();
        entity.setId(1L);
        entity.setNome("Pizzaria Italia");
        entity.setTaxaFrete(new BigDecimal("15.50"));
        entity.setCozinhaId(cozinha.getId());
        entity.setCozinha(cozinha);
        entity.setCidadeId(1L);
        entity.setDataCadastro(LocalDateTime.now());
        entity.setDataAtualizacao(LocalDateTime.now());
        entity.setEndereco(criarEnderecoEmbeddable());
        return entity;
    }

    /**
     * Creates a Restaurante JPA entity with custom nome and cozinha.
     * @param nome the name for the Restaurante
     * @param cozinha the CozinhaJpaEntity for the Restaurante
     * @return RestauranteJpaEntity with given name and cozinha
     */
    public static RestauranteJpaEntity umRestauranteJpaEntityComNomeECozinha(String nome, CozinhaJpaEntity cozinha) {
        RestauranteJpaEntity entity = new RestauranteJpaEntity();
        entity.setId(1L);
        entity.setNome(nome);
        entity.setTaxaFrete(new BigDecimal("15.50"));
        entity.setCozinhaId(cozinha.getId());
        entity.setCozinha(cozinha);
        entity.setCidadeId(1L);
        entity.setDataCadastro(LocalDateTime.now());
        entity.setDataAtualizacao(LocalDateTime.now());
        entity.setEndereco(criarEnderecoEmbeddable());
        return entity;
    }

    /**
     * Creates a valid Restaurante output DTO with all data.
     * @return RestauranteDTO with all required data
     */
    public static RestauranteDTO umRestauranteDTOValido() {
        RestauranteDTO dto = new RestauranteDTO();
        dto.setId(1L);
        dto.setNome("Pizzaria Italia");
        dto.setTaxaFrete(new BigDecimal("15.50"));
        dto.setLogradouro("Rua das Flores");
        dto.setNumero("123");
        dto.setComplemento("Apto 456");
        dto.setBairro("Centro");
        dto.setCep("01310-100");
        dto.setCidade(CidadeFixture.umaCidadeDTOValida());
        dto.setCozinha(CozinhaFixture.umaCozinhaDTOValida());
        return dto;
    }

    /**
     * Creates a Restaurante output DTO with custom nome.
     * @param nome the name for the Restaurante
     * @return RestauranteDTO with given name
     */
    public static RestauranteDTO umRestauranteDTOComNome(String nome) {
        RestauranteDTO dto = new RestauranteDTO();
        dto.setId(1L);
        dto.setNome(nome);
        dto.setTaxaFrete(new BigDecimal("15.50"));
        dto.setLogradouro("Rua das Flores");
        dto.setNumero("123");
        dto.setComplemento("Apto 456");
        dto.setBairro("Centro");
        dto.setCep("01310-100");
        dto.setCidade(CidadeFixture.umaCidadeDTOValida());
        dto.setCozinha(CozinhaFixture.umaCozinhaDTOValida());
        return dto;
    }

    /**
     * Helper method to create a valid EnderecoEmbeddable.
     * @return EnderecoEmbeddable with test data
     */
    private static EnderecoEmbeddable criarEnderecoEmbeddable() {
        EnderecoEmbeddable endereco = new EnderecoEmbeddable();
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero("123");
        endereco.setComplemento("Apto 456");
        endereco.setBairro("Centro");
        endereco.setCep("01310-100");
        return endereco;
    }
}
