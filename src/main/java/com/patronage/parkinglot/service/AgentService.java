package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.repository.AgentRepository;
import com.patronage.parkinglot.response.exception.AgentAlreadyExistsException;
import com.patronage.parkinglot.response.exception.AgentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AgentService implements IAgentService {
    private final AgentRepository repository;

    @Override
    public List<AgentDTO> getAgents() {
        List<Agent> agents = repository.findAll();
        List<AgentDTO> agentDTOS = new ArrayList<>();
        agents.forEach(agent -> {
            AgentDTO agentDTO = AgentDTO.builder().id(agent.getId()).name(agent.getName()).build();
            agentDTOS.add(agentDTO);
        });
        return agentDTOS;
    }

    @Override
    public AgentDTO getAgentByID(Long id) throws AgentNotFoundException {
        Optional<Agent> agent = Optional.ofNullable(repository.findById(id).orElseThrow(() -> new AgentNotFoundException(id)));
        return AgentDTO.builder().id(agent.orElse(null).getId()).name(agent.orElse(null).getName()).build();
    }

    @Override
    public AgentDTO getAgentByName(String name) throws AgentNotFoundException {
        Optional<Agent> agent = Optional.ofNullable(repository.findAgentByName(name).orElseThrow(() -> new AgentNotFoundException(name)));
        return AgentDTO.builder().id(agent.orElse(null).getId()).name(agent.orElse(null).getName()).build();
    }

    @Override
    public void createNewAgent(AgentDTO agentDTO) throws AgentAlreadyExistsException {
        if (nameExists(agentDTO.getName())) {
            throw new AgentAlreadyExistsException(agentDTO.getName());
        }
        Agent agent = new Agent();
        agent.setName(agentDTO.getName());
        repository.save(agent);
    }


    @Override
    public void deleteAgentByID(Long id) throws AgentNotFoundException {
        if (!idExists(id)) {
            throw new AgentNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteAgentByName(String name) throws AgentNotFoundException {
        if (!nameExists(name)) {
            throw new AgentNotFoundException(name);
        }
        repository.deleteAgentByName(name);

    }

    private boolean nameExists(String name) {
        return repository.findAgentByName(name).isPresent();
    }

    private boolean idExists(Long id) {
        return repository.findById(id).isPresent();
    }
}