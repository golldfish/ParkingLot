package com.patronage.parkinglot;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import com.patronage.parkinglot.repository.AgentRepository;
import com.patronage.parkinglot.repository.ParkingPlaceRepository;
import com.patronage.parkinglot.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDB(final AgentRepository agentRepository, final ParkingPlaceRepository parkingPlaceRepository, final ReservationRepository reservationRepository) {
        final Agent agent = createAgent(1L, "Bob");
        final Agent agent1 = createAgent(2L, "Logan");
        final Agent agent2 = createAgent(3L, "Alice");
        final ParkingPlace parkingPlace = createParkingPlace(4L, 1, 1, true);
        final ParkingPlace parkingPlace1 = createParkingPlace(5L, 2, 1, false);
        final ParkingPlace parkingPlace2 = createParkingPlace(6L, 3, 1, false);
        final ParkingPlace parkingPlace3 = createParkingPlace(7L, 4, 1, false);
        final ParkingPlace parkingPlace4 = createParkingPlace(8L, 1, 2, true);
        final Reservation reservation = createReservation(9L, agent, parkingPlace);
        return args -> {
            agentRepository.save(agent);
            agentRepository.save(agent1);
            agentRepository.save(agent2);
            parkingPlaceRepository.save(parkingPlace);
            parkingPlaceRepository.save(parkingPlace1);
            parkingPlaceRepository.save(parkingPlace2);
            parkingPlaceRepository.save(parkingPlace3);
            parkingPlaceRepository.save(parkingPlace4);
            reservationRepository.save(reservation);

        };
    }

    private Agent createAgent(final Long id, final String name) {
        final Agent agent = new Agent();
        agent.setId(id);
        agent.setName(name);
        return agent;
    }

    private ParkingPlace createParkingPlace(final Long id, final int placeNumber, final int tier, final boolean placeForDisabled) {
        final ParkingPlace place = new ParkingPlace();
        place.setId(id);
        place.setPlaceNumber(placeNumber);
        place.setTier(tier);
        place.setPlaceForDisabledPeople(placeForDisabled);
        place.setReservation(null);
        place.setReserved(false);
        return place;
    }

    private Reservation createReservation(final Long id, final Agent agent, final ParkingPlace place) {
        final Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setAgent(agent);
        reservation.setParkingPlace(place);
        place.setReserved(true);
        return reservation;
    }
}
