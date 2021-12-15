package com.patronage.parkinglot.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AgentIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnAllExistingAgents() throws Exception {
        this.mvc.perform(get("/agents"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Bob")))
                .andExpect(jsonPath("$[1].name", is("Logan")))
                .andExpect(jsonPath("$[2].name", is("Alice")))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldReturnAgent() throws Exception {
        this.mvc.perform(get("/agents/Bob"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"name\":\"Bob\"}"))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void shouldReturn404WhenThereIsNoSuchAgent() throws Exception {
        String error = this.mvc.perform(get("/agents/Lou"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn().getResolvedException().getMessage();
        Assertions.assertEquals("Agent with name: Lou is not found.", error);
    }

    @Test
    public void shouldAddNewAgent() throws Exception {
        final String content = this.mvc.perform(get("/agents"))
                .andReturn().getResponse().getContentAsString();
        final int beforeAdding = StringUtils.countOccurrencesOf(content, "name");
        this.mvc.perform(post("/agents?name=Lou"))
                .andExpect(status().isCreated());
        this.mvc.perform(get("/agents"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$.name", org.hamcrest.Matchers.is("Lou")))
                .andExpect(jsonPath("$[3].name").value("Lou"))
                .andExpect(jsonPath("$", hasSize(beforeAdding + 1)));
    }

}
