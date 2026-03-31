package com.ferry.food.domain.exceptions;

public class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static EntityNotFoundException forEntity(String entityName, Long id) {
        return new EntityNotFoundException(String.format("%s com ID %d não encontrado", entityName, id));
    }
}
