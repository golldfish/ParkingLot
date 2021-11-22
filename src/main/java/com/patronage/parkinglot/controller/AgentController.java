package com.patronage.parkinglot.controller;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.repository.AgentRepository;
import com.patronage.parkinglot.response.exception.AgentNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgentController {

    private final AgentRepository repository;

    AgentController(AgentRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/agents")
    List<Agent> all() {
        return repository.findAll();
    }

    @PostMapping("/agents/new")
    Agent newReservingAgent(@RequestBody Agent newAgent) {
        return repository.save(newAgent);
    }

    @GetMapping("/agents/id/{id}")
    Agent one(@PathVariable Long id) throws AgentNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new AgentNotFoundException(id));
    }

    @GetMapping("/agents/name/{name}")
    Agent one(@PathVariable String name) throws AgentNotFoundException {
        return repository.findAgentByName(name)
                .orElseThrow(() -> new AgentNotFoundException(name));
    }

    @DeleteMapping("/agents/id/{id}")
    void deleteAgent(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/agents/name/{name}")
    void deleteAgent(@PathVariable String name) {
        repository.deleteAgentByName(name);
    }

}
