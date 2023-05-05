package com.demo.drone.service;

import com.demo.drone.model.Drone;
import com.demo.drone.model.Medication;

import java.util.List;

public interface DroneDispatchService {
    List<Drone> getAllDrones();

    List<Medication> getAllMedications();
}
