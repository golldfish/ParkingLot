package com.patronage.parkinglot.response.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Agent not found")
public class AgentNotFoundException extends Exception {

    public AgentNotFoundException(Long id) {
        super("Could not find agent " + id);
    }

    public AgentNotFoundException(String name) {
        super("Could not find agent " + name);
    }
}
