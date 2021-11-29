package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReservationDTO {
    private Long id;
    private AgentDTO agentDTO;
    private ParkingPlaceDTO parkingPlaceDTO;
}
