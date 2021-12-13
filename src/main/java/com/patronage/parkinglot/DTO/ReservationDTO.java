package com.patronage.parkinglot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReservationDTO {
    private final Long id;
    private final String agentName;
    private final Long parkingPlaceId;
}
