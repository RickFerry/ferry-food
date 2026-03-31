package com.ferry.food.application.mappers.cidade;

import com.ferry.food.domain.entities.Cidade;
import com.ferry.food.domain.entities.Estado;
import com.ferry.food.application.dtos.input.cidade.CriarCidadeDTO;
import com.ferry.food.application.dtos.input.cidade.AtualizarCidadeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CidadeInputMapper {
    
    // This would typically be injected by Spring for entity lookups
    // For now, we'll leave it to the service layer to provide the Estado
    
    public Cidade toDomain(CriarCidadeDTO dto) {
        if (dto == null) {
            return null;
        }
        // The service layer should provide the Estado entity
        // This mapper receives it via method signature from the service
        throw new UnsupportedOperationException(
            "Use toDomain(CriarCidadeDTO dto, Estado estado) with State provided by service layer"
        );
    }
    
    public Cidade toDomain(CriarCidadeDTO dto, Estado estado) {
        if (dto == null || estado == null) {
            return null;
        }
        return Cidade.criarNova(dto.getNome(), estado);
    }
    
    public Cidade toDomain(AtualizarCidadeDTO dto) {
        // For updates, we expect the ID to be available from the service layer context
        if (dto == null) {
            return null;
        }
        throw new UnsupportedOperationException(
            "Use atualizarDomainAPartirDeDTO to update an existing Cidade"
        );
    }
    
    public void atualizarDomainAPartirDeDTO(AtualizarCidadeDTO dto, @MappingTarget Cidade cidade) {
        if (dto == null || cidade == null) {
            return;
        }
        cidade.atualizarNome(dto.getNome());
        // Note: estado update requires service layer lookup via estadoId
    }
    
    public void atualizarDomainAPartirDeDTO(AtualizarCidadeDTO dto, @MappingTarget Cidade cidade, Estado estado) {
        if (dto == null || cidade == null) {
            return;
        }
        cidade.atualizarNome(dto.getNome());
        if (estado != null) {
            cidade.atualizarEstado(estado);
        }
    }
}
