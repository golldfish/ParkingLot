package com.patronage.parkinglot.model.dto;

import com.patronage.parkinglot.dto.AgentDto;
import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.service.mapper.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AgentDtoTest {
    //private final MapStructMapper mapper = new MapStructMapper();
    private final Map mapper = Mappers.getMapper(Map.class);

    @Test
    @DisplayName("Convert Agent Entity to AgentDTO -> correct")
    public void whenConvertAgentToAgentDTO_thenCorrect() {
        //given
        final Agent agent = new Agent();
        agent.setId(1L);
        agent.setName("Kevin");
        //when
        final AgentDto agentDTO = mapper.convertToAgentDto(agent);
        //then
        assertEquals(agent.getName(), agentDTO.getName());
    }

    @Test
    @DisplayName("Convert AgentDTO to Agent Entity -> correct")
    public void whenConvertAgentDTOToAgentEntity_thenCorrect() {
        //given
        final AgentDto agentDTO = new AgentDto("Kevin");
        //when
        final Agent agent = mapper.convertToAgentEntity(agentDTO);
        //then
        assertEquals(agentDTO.getName(), agent.getName());
    }
}