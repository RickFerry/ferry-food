package com.ferry.food.application.mappers.restaurante;

import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.valueobjects.Endereco;
import com.ferry.food.application.dtos.input.restaurante.CriarRestauranteDTO;
import com.ferry.food.application.dtos.input.restaurante.AtualizarRestauranteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestauranteInputMapper {
    
    Restaurante toDomain(CriarRestauranteDTO dto);
    
    Restaurante toDomain(AtualizarRestauranteDTO dto);
    
    void atualizarDomainAPartirDeDTO(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);
}
