package com.patronage.parkinglot.repository;

import com.patronage.parkinglot.model.Agent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AgentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AgentRepository repository;

    @Test
    @DisplayName("Find Agent by name -> Agent")
    public void whenFindAgentByName_thenReturnEmployee() {
        //given
        final Agent agent = new Agent();
        agent.setName("Alex");
        entityManager.persist(agent);

        //when
        final Optional<Agent> found = repository.findAgentByName(agent.getName());
        //then
        assertThat(found.get().getName())
                .isEqualTo(agent.getName());
    }

    @Test
    @DisplayName("Delete Agent by name")
    public void whenDeleteAgentByName_thenDbIsEmpty() {
        //given
        final Agent agent = new Agent();
        agent.setName("Alex");
        entityManager.persist(agent);
        //when
        repository.deleteAgentByName(agent.getName());
        //then
        assertThat(repository.findAll()).isEmpty();
    }

}