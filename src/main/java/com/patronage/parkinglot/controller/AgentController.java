package com.patronage.parkinglot.controller;

import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.response.exception.AgentAlreadyExistsException;
import com.patronage.parkinglot.response.exception.AgentNotFoundException;
import com.patronage.parkinglot.service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AgentController {

    private final AgentService agentService;

    @GetMapping("/agents")
    ResponseEntity<List<AgentDTO>> all() {
        return new ResponseEntity<>(agentService.getAgents(), HttpStatus.OK);
    }


    @GetMapping("/agents/id/{id}")
    ResponseEntity<AgentDTO> one(@PathVariable Long id) throws AgentNotFoundException {
        return new ResponseEntity<>(agentService.getAgentByID(id), HttpStatus.OK);
    }

    @GetMapping("/agents/name/{name}")
    ResponseEntity<AgentDTO> one(@PathVariable String name) throws AgentNotFoundException {
        return new ResponseEntity<>(agentService.getAgentByName(name), HttpStatus.OK);
    }

    @PostMapping("/agents/new")
    ResponseEntity<Void> newAgent(@RequestBody AgentDTO newAgent) throws AgentAlreadyExistsException {
        agentService.createNewAgent(newAgent);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/agents/id/{id}")
    ResponseEntity<String> deleteAgent(@PathVariable Long id) throws AgentNotFoundException {
        agentService.deleteAgentByID(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @DeleteMapping("/agents/name/{name}")
    ResponseEntity<String> deleteAgent(@PathVariable String name) throws AgentNotFoundException {
        agentService.deleteAgentByName(name);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

}
