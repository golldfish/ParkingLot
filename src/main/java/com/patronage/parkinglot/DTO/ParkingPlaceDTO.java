package com.patronage.parkinglot.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ParkingPlaceDTO {
    private final Long id;
    private final int placeNumber;
    private final int tier;
    private final boolean placeForDisabledPeople;
}
