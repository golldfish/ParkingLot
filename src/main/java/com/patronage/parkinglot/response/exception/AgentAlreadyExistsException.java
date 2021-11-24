package com.patronage.parkinglot.response.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Agent already exists")
public class AgentAlreadyExistsException extends Exception {
    public AgentAlreadyExistsException(String name) {
        super("Agent with name: " + name + " already exists in db.");
    }

}
