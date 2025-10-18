package com.ferry.food.domain.exception;

public class MyEntityNotFoundException extends RuntimeException {
    public MyEntityNotFoundException(String msg) {
        super(msg);
    }
}
