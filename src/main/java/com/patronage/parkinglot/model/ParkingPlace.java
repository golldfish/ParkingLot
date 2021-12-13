package com.patronage.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "parking_place")
public class ParkingPlace implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "place_number")
    private int placeNumber;

    @NotNull
    private int tier;

    @NotNull
    private boolean placeForDisabledPeople;

    @OneToOne(mappedBy = "parkingPlace")
    private Reservation reservation;

    public static ParkingPlace createParkingPlace(final Long id, final int placeNumber, final int tier, final boolean placeForDisabled) {
        final ParkingPlace place = new ParkingPlace();
        place.setId(id);
        place.setPlaceNumber(placeNumber);
        place.setTier(tier);
        place.setPlaceForDisabledPeople(placeForDisabled);
        place.setReservation(null);
        return place;
    }
}
