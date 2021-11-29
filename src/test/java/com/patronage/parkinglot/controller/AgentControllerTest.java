package com.patronage.parkinglot.controller;

import com.patronage.parkinglot.model.DTO.AgentDTO;
import com.patronage.parkinglot.service.AgentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(AgentController.class)
public class AgentControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AgentService agentService;

    @Test
    public void getAllAgents() throws Exception {
        //given
        AgentDTO agentDTO = new AgentDTO();
        agentDTO.setId(1L);
        agentDTO.setName("Bob");
        List<AgentDTO> agents = List.of(agentDTO);

        when(agentService.getAgents()).thenReturn((agents));

        this.mvc.perform(get("/agents"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(agentDTO.getName())));
    }

}