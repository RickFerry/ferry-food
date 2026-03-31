package com.ferry.food.application.mappers.cozinha;

import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.application.dtos.input.cozinha.CriarCozinhaDTO;
import com.ferry.food.application.dtos.input.cozinha.AtualizarCozinhaDTO;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CozinhaInputMapper {
    
    public Cozinha toDomain(CriarCozinhaDTO dto) {
        if (dto == null) {
            return null;
        }
        return Cozinha.criarNova(dto.getNome());
    }
    
    public Cozinha toDomain(AtualizarCozinhaDTO dto) {
        // For updates, we expect the ID to be available from the service layer context
        // This method should rarely be called directly; atualizarDomainAPartirDeDTO is preferred
        if (dto == null) {
            return null;
        }
        return Cozinha.criarNova(dto.getNome());
    }
    
    public void atualizarDomainAPartirDeDTO(AtualizarCozinhaDTO dto, @MappingTarget Cozinha cozinha) {
        if (dto == null || cozinha == null) {
            return;
        }
        cozinha.atualizarNome(dto.getNome());
    }
}
