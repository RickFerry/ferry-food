package com.ferry.food.application.mappers.restaurante;

import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import com.ferry.food.application.mappers.cozinha.CozinhaOutputMapper;
import com.ferry.food.application.mappers.cidade.CidadeOutputMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CozinhaOutputMapper.class, CidadeOutputMapper.class})
public interface RestauranteOutputMapper {
    
    @Mapping(source = "nome.valor", target = "nome")
    @Mapping(source = "taxaFrete.valor", target = "taxaFrete")
    @Mapping(source = "endereco.logradouro", target = "logradouro")
    @Mapping(source = "endereco.numero", target = "numero")
    @Mapping(source = "endereco.complemento", target = "complemento")
    @Mapping(source = "endereco.bairro", target = "bairro")
    @Mapping(source = "endereco.cep", target = "cep")
    RestauranteDTO toDTO(Restaurante restaurante);
}
