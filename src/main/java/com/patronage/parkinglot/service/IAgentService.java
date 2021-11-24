package com.patronage.parkinglot.service;

import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.response.exception.AgentAlreadyExistsException;
import com.patronage.parkinglot.response.exception.AgentNotFoundException;

import java.util.List;

public interface IAgentService {
    public List<AgentDTO> getAgents();

    public void createNewAgent(AgentDTO agentDTO) throws AgentAlreadyExistsException;

    public AgentDTO getAgentByID(Long id) throws AgentNotFoundException;

    public AgentDTO getAgentByName(String name) throws AgentNotFoundException;

    public void deleteAgentByID(Long id) throws AgentNotFoundException;

    public void deleteAgentByName(String name) throws AgentNotFoundException;


}
