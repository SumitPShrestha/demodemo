package com.demo.drone.service;

import com.demo.drone.model.Drone;
import com.demo.drone.model.Medication;
import com.demo.drone.repository.DroneRepository;
import com.demo.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DroneDispatchServiceImpl implements DroneDispatchService {

    @Autowired
    DroneRepository droneRepository;
    @Autowired
    MedicationRepository medicationRepository;

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
}
