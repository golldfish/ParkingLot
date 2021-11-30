package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.service.mapper.MapStructMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AgentDTOTest {
    private final MapStructMapperImpl mapper = new MapStructMapperImpl();

    @Test
    @DisplayName("Convert Agent Entity to AgentDTO -> correct")
    public void whenConvertAgentToAgentDTO_thenCorrect() {
        //given
        final Agent agent = new Agent();
        agent.setId(1L);
        agent.setName("Kevin");
        //when
        final AgentDTO agentDTO = mapper.convertAgentToDto(agent);
        //then
        assertEquals(agent.getId(), agentDTO.getId());
        assertEquals(agent.getName(), agentDTO.getName());
    }

    @Test
    @DisplayName("Convert AgentDTO to Agent Entity -> correct")
    public void whenConvertAgentDTOToAgentEntity_thenCorrect() {
        //given
        final AgentDTO agentDTO = new AgentDTO(1L, "Kevin");
        //when
        final Agent agent = mapper.convertToAgentEntity(agentDTO);
        //then
        assertEquals(agentDTO.getId(), agent.getId());
        assertEquals(agentDTO.getName(), agent.getName());
    }
}