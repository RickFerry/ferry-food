package com.ferry.food.domain.exceptions;

public class EntityInUseException extends DomainException {
    public EntityInUseException(String message) {
        super(message);
    }

    public EntityInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}
