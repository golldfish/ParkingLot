package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.model.Agent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.patronage.parkinglot.service.mapper.Mapper.convertAgentToDto;
import static com.patronage.parkinglot.service.mapper.Mapper.convertToAgentEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AgentDTOTest {

    @Test
    @DisplayName("Convert Agent Entity to AgentDTO -> correct")
    public void whenConvertAgentToAgentDTO_thenCorrect() {
        //given
        Agent agent = new Agent();
        agent.setId(1L);
        agent.setName("Kevin");
        //when
        AgentDTO agentDTO = convertAgentToDto(agent);
        //then
        assertEquals(agent.getId(), agentDTO.getId());
        assertEquals(agent.getName(), agentDTO.getName());
    }

    @Test
    @DisplayName("Convert AgentDTO to Agent Entity -> correct")
    public void whenConvertAgentDTOToAgentEntity_thenCorrect() {
        //given
        AgentDTO agentDTO = new AgentDTO();
        agentDTO.setId(1L);
        agentDTO.setName("Kevin");
        //when
        Agent agent = convertToAgentEntity(agentDTO);
        //then
        assertEquals(agentDTO.getId(), agent.getId());
        assertEquals(agentDTO.getName(), agent.getName());
    }
}