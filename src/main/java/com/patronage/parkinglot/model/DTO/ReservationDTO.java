package com.patronage.parkinglot.model.DTO;

import lombok.Data;

@Data
public class ReservationDTO {
    private AgentDTO agentDTO;
    private ParkingPlaceDTO parkingLotDTO;
}
