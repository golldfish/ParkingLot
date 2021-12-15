package com.patronage.parkinglot.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent", referencedColumnName = "name")
    private Agent agent;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "parkingPlace", referencedColumnName = "id")
    private ParkingPlace parkingPlace;

    public static Reservation createReservation(final Long id, final Agent agent, final ParkingPlace place) {
        return new Reservation(id, agent, place);
    }


}
