package com.patronage.parkinglot.repository;

import com.patronage.parkinglot.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}

