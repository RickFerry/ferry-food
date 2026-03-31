package com.ferry.food.adapter.output.persistence.mapper;

import com.ferry.food.adapter.output.persistence.entity.PedidoJpaEntity;
import com.ferry.food.domain.entities.Pedido;
import com.ferry.food.domain.entities.Restaurante;
import com.ferry.food.domain.valueobjects.Endereco;
import com.ferry.food.domain.valueobjects.StatusPedido;
import com.ferry.food.domain.valueobjects.Taxa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class PedidoJpaMapper {
    
    @Autowired
    private RestauranteJpaMapper restauranteMapper;
    
    public Pedido toDomainEntity(PedidoJpaEntity jpaEntity) {
        if (jpaEntity == null) {
            return null;
        }
        
        Restaurante restaurante = null;
        if (jpaEntity.restaurante != null) {
            restaurante = restauranteMapper.toDomainEntity(jpaEntity.restaurante);
        }
        
        Endereco enderecoEntrega = null;
        if (jpaEntity.endereco != null) {
            enderecoEntrega = new Endereco(
                jpaEntity.endereco.logradouro,
                jpaEntity.endereco.numero,
                jpaEntity.endereco.complemento,
                jpaEntity.endereco.bairro,
                jpaEntity.endereco.cep,
                jpaEntity.endereco.cidadeId
            );
        }
        
        // Create pedido with criarNovo first
        Pedido pedido = Pedido.criarNovo(
            new Taxa(jpaEntity.subtotal),
            new Taxa(jpaEntity.taxaFrete),
            enderecoEntrega,
            restaurante
        );
        
        // Use reflection to set the remaining fields
        try {
            setFieldViaReflection(pedido, "id", jpaEntity.id);
            setFieldViaReflection(pedido, "status", StatusPedido.valueOf(jpaEntity.status));
            setFieldViaReflection(pedido, "dataCriacao", jpaEntity.dataCriacao);
            setFieldViaReflection(pedido, "dataConfirmacao", jpaEntity.dataConfirmacao);
            setFieldViaReflection(pedido, "dataEntrega", jpaEntity.dataEntrega);
            setFieldViaReflection(pedido, "dataCancelamento", jpaEntity.dataCancelamento);
        } catch (Exception e) {
            throw new RuntimeException("Error mapping Pedido", e);
        }
        
        return pedido;
    }
    
    private void setFieldViaReflection(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    
    public PedidoJpaEntity toJpaEntity(Pedido domainEntity) {
        if (domainEntity == null) {
            return null;
        }
        PedidoJpaEntity entity = new PedidoJpaEntity();
        entity.id = domainEntity.getId();
        entity.subtotal = domainEntity.getSubtotal().getValor();
        entity.taxaFrete = domainEntity.getTaxaFrete().getValor();
        entity.valorTotal = domainEntity.getValorTotal().getValor();
        entity.status = domainEntity.getStatus().name();
        entity.dataCriacao = domainEntity.getDataCriacao();
        entity.dataConfirmacao = domainEntity.getDataConfirmacao();
        entity.dataEntrega = domainEntity.getDataEntrega();
        entity.dataCancelamento = domainEntity.getDataCancelamento();
        
        if (domainEntity.getRestaurante() != null) {
            entity.restauranteId = domainEntity.getRestaurante().getId();
        }
        
        if (domainEntity.getEndereco() != null) {
            entity.endereco = new com.ferry.food.adapter.output.persistence.entity.EnderecoEmbeddable();
            entity.endereco.logradouro = domainEntity.getEndereco().getLogradouro();
            entity.endereco.numero = domainEntity.getEndereco().getNumero();
            entity.endereco.complemento = domainEntity.getEndereco().getComplemento();
            entity.endereco.bairro = domainEntity.getEndereco().getBairro();
            entity.endereco.cep = domainEntity.getEndereco().getCep();
            entity.endereco.cidadeId = domainEntity.getEndereco().getCidadeId();
        }
        
        return entity;
    }
}
