package com.demo.drone.controller;

import com.demo.drone.model.Dispatch;
import com.demo.drone.model.Drone;
import com.demo.drone.model.Medication;
import com.demo.drone.service.DroneDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/register-drone/{serialNumber}")
    public ResponseEntity<Dispatch> doRegisterDrone(@PathVariable String serialNumber) {
        Dispatch savedDispatch = droneDispatchService.registerDrone(serialNumber);
        return ResponseEntity.ok(savedDispatch);
    }


}
