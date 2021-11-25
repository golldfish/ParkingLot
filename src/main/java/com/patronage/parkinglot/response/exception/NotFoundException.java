package com.patronage.parkinglot.response.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not found")
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}