package com.patronage.parkinglot;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.repository.AgentRepository;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDB(AgentRepository agentRepository, ParkingPlaceRepository parkingLotRepository) {
        Agent agent = createAgent(1L, "Bob");
        Agent agent1 = createAgent(2L, "Logan");
        Agent agent2 = createAgent(3L, "Alice");
        ParkingPlace parkingLot = createParkingLot(20L, 1, 1, true);
        ParkingPlace parkingLot1 = createParkingLot(21L, 2, 1, false);
        ParkingPlace parkingLot2 = createParkingLot(22L, 3, 1, false);
        ParkingPlace parkingLot3 = createParkingLot(23L, 4, 1, false);

        return args -> {
            agentRepository.save(agent);
            agentRepository.save(agent1);
            agentRepository.save(agent2);
            parkingLotRepository.save(parkingLot);
            parkingLotRepository.save(parkingLot1);
            parkingLotRepository.save(parkingLot2);
            parkingLotRepository.save(parkingLot3);
        };
    }

    private Agent createAgent(Long id, String name) {
        Agent agent = new Agent();
        agent.setId(id);
        agent.setName(name);
        return agent;
    }

    private ParkingPlace createParkingLot(Long id, int placeNumber, int tier, boolean placeForDisabled) {
        ParkingPlace parkingLot = new ParkingPlace();
        parkingLot.setId(id);
        parkingLot.setPlaceNumber(placeNumber);
        parkingLot.setTier(tier);
        parkingLot.setPlaceForDisabledPeople(placeForDisabled);
        parkingLot.setReservation(null);
        return parkingLot;
    }
}
