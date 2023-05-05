package com.demo.drone.service;

import com.demo.drone.exception.DroneRegistrationException;
import com.demo.drone.model.Dispatch;
import com.demo.drone.model.Drone;
import com.demo.drone.model.Medication;
import com.demo.drone.repository.DispatchRepository;
import com.demo.drone.repository.DroneRepository;
import com.demo.drone.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DroneDispatchServiceImpl implements DroneDispatchService {

    @Autowired
    DroneRepository droneRepository;
    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    DispatchRepository dispatchRepository;

    @Override
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public Dispatch registerDrone(String serialNumber) {
        boolean isAlreadyRegistered = dispatchRepository.findBySerialNumber(serialNumber) != null;
        if (isAlreadyRegistered) {
            throw new DroneRegistrationException("drone-already-registered", String.format("Drone with SN %s is already registered", serialNumber), HttpStatus.ALREADY_REPORTED);
        }
        Drone droneToLoad = droneRepository.findBySerialNumber(serialNumber);
        Dispatch dispatch = new Dispatch();
        dispatch.setDrone(droneToLoad);
        return dispatchRepository.save(dispatch);
    }
}
