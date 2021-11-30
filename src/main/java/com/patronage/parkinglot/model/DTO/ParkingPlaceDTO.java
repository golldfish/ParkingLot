package com.patronage.parkinglot.model.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ParkingPlaceDTO {
    private final Long id;
    private final int placeNumber;
    private final int tier;
    private final boolean placeForDisabledPeople;
    private final boolean isReserved;
}
