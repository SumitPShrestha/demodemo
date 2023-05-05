package com.demo.drone.controller;

import com.demo.drone.model.Drone;
import com.demo.drone.model.Medication;
import com.demo.drone.service.DroneDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DroneDispatchController {

    @Autowired
    DroneDispatchService droneDispatchService;

    @RequestMapping("/drones")
    public List<Drone> getAllDrones() {
        return droneDispatchService.getAllDrones();
    }

    @RequestMapping("/medications")
    public List<Medication> getAllMedicines() {
        return droneDispatchService.getAllMedications();
    }

}
