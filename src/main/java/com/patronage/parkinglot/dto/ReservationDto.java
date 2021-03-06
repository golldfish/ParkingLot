package com.patronage.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReservationDto {
    private final Long id;
    private final String agentName;
    private final Long parkingPlaceId;
}
