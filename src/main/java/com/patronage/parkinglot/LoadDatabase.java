package com.patronage.parkinglot;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingLot;
import com.patronage.parkinglot.model.Reservation;
import com.patronage.parkinglot.repository.AgentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDB(AgentRepository agentRepository) {
        Agent agent = new Agent();
        agent.setId(1L);
        agent.setName("Bob");
        Agent agent1 = new Agent();
        agent1.setId(2L);
        agent1.setName("Logan");

        return args -> {
            agentRepository.save(agent);
            agentRepository.save(agent1);
        };
    }
}
