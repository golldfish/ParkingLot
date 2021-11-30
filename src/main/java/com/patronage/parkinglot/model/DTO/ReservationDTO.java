package com.patronage.parkinglot.model.DTO;

import com.patronage.parkinglot.model.Agent;
import com.patronage.parkinglot.model.ParkingPlace;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReservationDTO {
    private final Long id;
    private final AgentDTO agentDTO;
    private final ParkingPlaceDTO parkingPlaceDTO;
}
