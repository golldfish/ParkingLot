package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.DTO.ReservationDTO;
import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import com.patronage.parkinglot.model.Reservation;
import com.patronage.parkinglot.service.mapper.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationDTOTest {

    //private final MapStructMapper mapper = new MapStructMapper();
    private final Map mapper = Mappers.getMapper(Map.class);

    @Test
    @DisplayName("Convert Reservation Entity to ReservationDTO -> correct")
    public void whenConvertReservationToReservationDTO_thenCorrect() {
        //given
        final Agent agent = Agent.createAgent(1L, "Alex");
        final ParkingPlace place = ParkingPlace.createParkingPlace(5L, 3, 2, false);
        final Reservation reservation = Reservation.createReservation(2L, agent, place);
        //when
        final ReservationDTO reservationDTO = mapper.convertRepositoryToDTO(reservation);
        //then
        assertEquals(reservation.getId(), reservationDTO.getId());
        assertEquals(reservation.getAgent().getName(), reservationDTO.getAgentName());
        assertEquals(reservation.getParkingPlace().getId(), reservationDTO.getParkingPlaceId());
    }

    @Test
    @DisplayName("Convert ReservationDTO to Reservation Entity -> correct")
    public void whenConvertReservationDTOToReservationEntity_thenCorrect() {
        //given
        final ReservationDTO reservationDTO = new ReservationDTO(3L, "Alex", 5L);
        //when
        final Reservation reservation = mapper.convertToReservationEntity(reservationDTO);
        //then
        assertEquals(reservationDTO.getId(), reservation.getId());
        assertEquals(reservationDTO.getAgentName(), reservation.getAgent().getName());
        assertEquals(reservationDTO.getParkingPlaceId(), reservation.getParkingPlace().getId());

    }

}