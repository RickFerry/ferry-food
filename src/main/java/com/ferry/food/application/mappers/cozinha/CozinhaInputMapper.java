package com.ferry.food.application.mappers.cozinha;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.application.dtos.input.cozinha.CriarCozinhaDTO;
import com.ferry.food.application.dtos.input.cozinha.AtualizarCozinhaDTO;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CozinhaInputMapper {
    
    Cozinha toDomain(CriarCozinhaDTO dto);
    
    Cozinha toDomain(AtualizarCozinhaDTO dto);
    
    void atualizarDomainAPartirDeDTO(AtualizarCozinhaDTO dto, @MappingTarget Cozinha cozinha);
}
