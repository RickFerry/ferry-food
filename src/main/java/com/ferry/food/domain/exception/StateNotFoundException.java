package com.ferry.food.domain.exception;

import static java.lang.String.format;

public class StateNotFoundException extends MyEntityNotFoundException {
    public StateNotFoundException(String msg) {
        super(msg);
    }

    public StateNotFoundException(Long stateId) {
        this(format("No registration of state with code %d exists", stateId));
    }
}
