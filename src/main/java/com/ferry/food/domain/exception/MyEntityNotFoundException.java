package com.ferry.food.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MyEntityNotFoundException extends BusinessEception {
    public MyEntityNotFoundException(String msg) {
        super(msg);
    }
}
