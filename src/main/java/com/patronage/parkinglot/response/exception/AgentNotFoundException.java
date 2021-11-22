package com.patronage.parkinglot.response.exception;

public class AgentNotFoundException extends Exception {

    public AgentNotFoundException(Long id) {
        super("Could not find agent " + id);
    }

    public AgentNotFoundException(String name) {
        super("Could not find agent " + name);
    }
}
