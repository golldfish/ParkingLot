package com.patronage.parkinglot.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "agent")
public class Agent implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", unique = true)
    @Size(min = 2, max = 20)
    @NotBlank
    @NotNull
    private String name;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations;

    public static Agent createAgent(final Long id, final String name) {
        final Agent agent = new Agent();
        agent.setId(id);
        agent.setName(name);
        return agent;
    }

}
