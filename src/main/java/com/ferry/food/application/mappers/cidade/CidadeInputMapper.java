package com.ferry.food.application.mappers.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.application.dtos.input.cidade.CriarCidadeDTO;
import com.ferry.food.application.dtos.input.cidade.AtualizarCidadeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CidadeInputMapper {
    
    Cidade toDomain(CriarCidadeDTO dto);
    
    Cidade toDomain(AtualizarCidadeDTO dto);
    
    void atualizarDomainAPartirDeDTO(AtualizarCidadeDTO dto, @MappingTarget Cidade cidade);
}
