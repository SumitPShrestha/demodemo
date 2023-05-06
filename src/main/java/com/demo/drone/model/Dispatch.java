package com.demo.drone.model;


import com.google.gson.Gson;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<String> getMedications() {
        if (medications.get(0).contains(",")) {
            String[] userArray = new Gson().fromJson(medications.get(0), String[].class);
            return Arrays.stream(userArray).toList();
        }
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public Timestamp getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(Timestamp deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public Timestamp getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(Timestamp deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }
}

