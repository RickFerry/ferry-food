package com.ferry.food.application.mappers.estado;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstadoOutputMapper {
    
    @Mapping(source = "nome.valor", target = "nome")
    EstadoDTO toDTO(Estado estado);
}
