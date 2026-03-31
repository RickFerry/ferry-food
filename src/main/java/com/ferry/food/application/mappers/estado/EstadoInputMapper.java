package com.ferry.food.application.mappers.estado;

import com.ferry.food.domain.entities.Estado;
import com.ferry.food.application.dtos.input.estado.CriarEstadoDTO;
import com.ferry.food.application.dtos.input.estado.AtualizarEstadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class EstadoInputMapper {
    
    public Estado toDomain(CriarEstadoDTO dto) {
        if (dto == null) {
            return null;
        }
        return Estado.criarNovo(dto.getNome());
    }
    
    public Estado toDomain(AtualizarEstadoDTO dto) {
        // For updates, we expect the ID to be available from the service layer context
        // This method should rarely be called directly; atualizarDomainAPartirDeDTO is preferred
        if (dto == null) {
            return null;
        }
        return Estado.criarNovo(dto.getNome());
    }
    
    public void atualizarDomainAPartirDeDTO(AtualizarEstadoDTO dto, @MappingTarget Estado estado) {
        if (dto == null || estado == null) {
            return;
        }
        estado.atualizarNome(dto.getNome());
    }
}
