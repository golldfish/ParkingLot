package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.model.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationDTOTest {

    private final ModelMapper mapper = new ModelMapper();


    @Test
    @DisplayName("Convert Reservation Entity to ReservationDTO -> correct")
    public void whenConvertReservationToReservationDTO_thenCorrect() {
        //given
        Reservation reservation = new Reservation();
        reservation.setId(3L);

        //when
        ReservationDTO reservationDTO = mapper.map(reservation, ReservationDTO.class);
        //then
        assertEquals(reservation.getId(), reservationDTO.getId());
    }

    @Test
    @DisplayName("Convert ReservationDTO to Reservation Entity -> correct")
    public void whenConvertReservationDTOToReservationEntity_thenCorrect() {
        //given
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(3L);
        //when
        Reservation reservation = mapper.map(reservationDTO, Reservation.class);
        //then
        assertEquals(reservationDTO.getId(), reservation.getId());
    }

}