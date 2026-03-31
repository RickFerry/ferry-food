package com.ferry.food.application.mappers.estado;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.application.dtos.input.estado.CriarEstadoDTO;
import com.ferry.food.application.dtos.input.estado.AtualizarEstadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EstadoInputMapper {
    
    Estado toDomain(CriarEstadoDTO dto);
    
    Estado toDomain(AtualizarEstadoDTO dto);
    
    void atualizarDomainAPartirDeDTO(AtualizarEstadoDTO dto, @MappingTarget Estado estado);
}
