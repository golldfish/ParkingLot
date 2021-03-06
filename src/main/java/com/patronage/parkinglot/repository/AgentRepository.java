package com.patronage.parkinglot.repository;

import com.patronage.parkinglot.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findAgentByName(String name);

    boolean existsAgentByName(String name);

    void deleteAgentByName(String name);


}
