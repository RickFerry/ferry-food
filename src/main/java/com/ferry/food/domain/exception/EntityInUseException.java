package com.ferry.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntityInUseException extends BusinessEception {
    public EntityInUseException(String message) {
        super(message);
    }
}
