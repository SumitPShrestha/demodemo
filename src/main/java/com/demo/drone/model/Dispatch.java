package com.demo.drone.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "dispatch")
public class Dispatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;


    @Column(name = "total_weight")
    private float totalWeight;
    private List<String> medications;

    private java.sql.Timestamp deliveryStartTime;
    private java.sql.Timestamp deliveryEndTime;

}

