package com.patronage.parkinglot.configuration;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import com.patronage.parkinglot.repository.AgentRepository;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.patronage.parkinglot.model.Agent.createAgent;
import static com.patronage.parkinglot.model.ParkingPlace.createParkingPlace;
import static com.patronage.parkinglot.model.Reservation.createReservation;

@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDB(final AgentRepository agentRepository, final ParkingPlaceRepository parkingPlaceRepository, final ReservationRepository reservationRepository) {
        final Agent agent = createAgent(1L, "Bob");

        return args -> {
            agentRepository.save(agent);
            agentRepository.save(createAgent(2L, "Logan"));
            agentRepository.save(createAgent(3L, "Alice"));
            parkingPlaceRepository.save(createParkingPlace(5L, 2, 1, false));
            parkingPlaceRepository.save(createParkingPlace(6L, 3, 1, false));
            parkingPlaceRepository.save(createParkingPlace(7L, 4, 1, false));
            parkingPlaceRepository.save(createParkingPlace(8L, 1, 2, true));
            reservationRepository.save(createReservation(9L, agent, createParkingPlace(4L, 1, 1, true)));
        };
    }


}
