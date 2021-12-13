package com.patronage.parkinglot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(final String message) {
        super(message);
    }
}
