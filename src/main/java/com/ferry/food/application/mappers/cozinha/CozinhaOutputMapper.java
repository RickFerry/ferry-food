package com.ferry.food.application.mappers.cozinha;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CozinhaOutputMapper {
    
    @Mapping(source = "nome.valor", target = "nome")
    CozinhaDTO toDTO(Cozinha cozinha);
}
