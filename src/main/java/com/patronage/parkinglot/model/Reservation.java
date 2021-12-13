package com.patronage.parkinglot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkingPlace", referencedColumnName = "id")
    private ParkingPlace parkingPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent", referencedColumnName = "name")
    private Agent agent;

    public static Reservation createReservation(final Long id, final Agent agent, final ParkingPlace place) {
        final Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setAgent(agent);
        reservation.setParkingPlace(place);
        return reservation;
    }


}
