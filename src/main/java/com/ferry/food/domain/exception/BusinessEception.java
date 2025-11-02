package com.ferry.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessEception extends RuntimeException {
    public BusinessEception(String message) {
        super(message);
    }

    public BusinessEception(String message, Throwable cause) {
        super(message, cause);
    }
}
