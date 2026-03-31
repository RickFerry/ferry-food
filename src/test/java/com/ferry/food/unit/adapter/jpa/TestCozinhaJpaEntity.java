package com.ferry.food.unit.adapter.jpa;

import com.ferry.food.adapter.output.persistence.entity.CozinhaJpaEntity;
import com.ferry.food.fixtures.CozinhaFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("CozinhaJpaEntity Tests")
class TestCozinhaJpaEntity {

    @Test
    @DisplayName("should create and map CozinhaJpaEntity")
    void testCozinhaJpaEntity_PodeSerMapeada() {
        // Arrange & Act
        CozinhaJpaEntity entity = CozinhaFixture.umaCozinhaJpaEntity();

        // Assert
        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getNome()).isEqualTo("Italiana");
    }

    @Test
    @DisplayName("should set and get Cozinha properties")
    void testCozinhaJpaEntity_PropriedadesSetadas() {
        // Arrange
        CozinhaJpaEntity entity = new CozinhaJpaEntity();
        
        // Act
        entity.setId(5L);
        entity.setNome("Chinesa");

        // Assert
        assertThat(entity.getId()).isEqualTo(5L);
        assertThat(entity.getNome()).isEqualTo("Chinesa");
    }

    @Test
    @DisplayName("should have all required fields")
    void testCozinhaJpaEntity_ContemCamposObrigatorios() {
        // Arrange
        CozinhaJpaEntity entity = CozinhaFixture.umaCozinhaJpaEntity();

        // Assert
        assertThat(entity).hasFieldOrPropertyWithValue("id", 1L);
        assertThat(entity).hasFieldOrPropertyWithValue("nome", "Italiana");
    }
}
