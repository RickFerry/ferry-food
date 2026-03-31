package com.ferry.food.application.mappers.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import com.ferry.food.application.mappers.estado.EstadoOutputMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EstadoOutputMapper.class)
public interface CidadeOutputMapper {
    
    @Mapping(source = "nome.valor", target = "nome")
    @Mapping(source = "estado", target = "estado")
    CidadeDTO toDTO(Cidade cidade);
}
