package com.demo.drone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "drone")
public class Drone implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column(name = "weight_limit")
    private int weightLimit;
    @Column(name = "battery_capacity")
    private int batteryCapacity;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private DroneState state;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +"\n"+
                ", serialNumber='" + serialNumber +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryCapacity=" + batteryCapacity +
                ", state=" + state +
                '}';
    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
