package com.ferry.food.application.mappers.restaurante;

import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.entities.Cozinha;
import com.ferry.food.domain.valueobjects.Endereco;
import com.ferry.food.application.dtos.input.restaurante.CriarRestauranteDTO;
import com.ferry.food.application.dtos.input.restaurante.AtualizarRestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class RestauranteInputMapper {
    
    public Restaurante toDomain(CriarRestauranteDTO dto) {
        if (dto == null) {
            return null;
        }
        // The service layer should provide the Cozinha entity
        throw new UnsupportedOperationException(
            "Use toDomain(CriarRestauranteDTO dto, Cozinha cozinha) with Cozinha provided by service layer"
        );
    }
    
    public Restaurante toDomain(CriarRestauranteDTO dto, Cozinha cozinha) {
        if (dto == null || cozinha == null) {
            return null;
        }
        return Restaurante.criarNovo(
            dto.getNome(),
            dto.getTaxaFrete(),
            dto.getLogradouro(),
            dto.getNumero(),
            dto.getComplemento(),
            dto.getBairro(),
            dto.getCep(),
            dto.getCidadeId(),
            cozinha
        );
    }
    
    public Restaurante toDomain(AtualizarRestauranteDTO dto) {
        // For updates, we expect the ID and other entities to be available from the service layer context
        if (dto == null) {
            return null;
        }
        throw new UnsupportedOperationException(
            "Use atualizarDomainAPartirDeDTO to update an existing Restaurante"
        );
    }
    
    public void atualizarDomainAPartirDeDTO(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante) {
        if (dto == null || restaurante == null) {
            return;
        }
        restaurante.atualizarDados(
            dto.getNome(),
            dto.getTaxaFrete(),
            dto.getLogradouro(),
            dto.getNumero(),
            dto.getComplemento(),
            dto.getBairro(),
            dto.getCep(),
            dto.getCidadeId()
        );
        // Note: cozinha update requires service layer lookup via cozinhaId
    }
    
    public void atualizarDomainAPartirDeDTO(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante, Cozinha cozinha) {
        if (dto == null || restaurante == null) {
            return;
        }
        restaurante.atualizarDados(
            dto.getNome(),
            dto.getTaxaFrete(),
            dto.getLogradouro(),
            dto.getNumero(),
            dto.getComplemento(),
            dto.getBairro(),
            dto.getCep(),
            dto.getCidadeId()
        );
        if (cozinha != null) {
            restaurante.atualizarCozinha(cozinha);
        }
    }
}
