package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.model.Reservation;
import com.patronage.parkinglot.service.mapper.MapStructMapperImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationDTOTest {

    private final MapStructMapperImpl mapper = new MapStructMapperImpl();


    @Test
    @DisplayName("Convert Reservation Entity to ReservationDTO -> correct")
    public void whenConvertReservationToReservationDTO_thenCorrect() {
        //given
        final Reservation reservation = new Reservation();
        reservation.setId(3L);
        //when
        final ReservationDTO reservationDTO = mapper.convertRepositoryToDTO(reservation);
        //then
        assertEquals(reservation.getId(), reservationDTO.getId());
    }

    @Test
    @DisplayName("Convert ReservationDTO to Reservation Entity -> correct")
    public void whenConvertReservationDTOToReservationEntity_thenCorrect() {
        //given
        final ReservationDTO reservationDTO = new ReservationDTO(3L, null, null);
        //when
        final Reservation reservation = mapper.convertToReservationEntity(reservationDTO);
        //then
        assertEquals(reservationDTO.getId(), reservation.getId());
    }

}