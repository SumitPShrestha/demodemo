package com.demo.drone.service;

import com.demo.drone.model.Dispatch;
import com.demo.drone.model.Drone;
import com.demo.drone.model.DroneState;
import com.demo.drone.model.Medication;
import com.demo.drone.repository.DispatchRepository;
import com.demo.drone.repository.DroneRepository;
import com.demo.drone.repository.MedicationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@Log4j2
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
        Drone droneToLoad = droneRepository.findBySerialNumber(serialNumber);
        Dispatch dispatch = new Dispatch();
        dispatch.setDrone(droneToLoad);
        return dispatchRepository.save(dispatch);
    }

    @Override
    public Dispatch getDispatchBySerialNumber(String serialNumber) {
        return dispatchRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public Drone changeDroneStatus(Drone drone, DroneState stateToChange) {
        Drone droneToSave;
        try {
            droneToSave = (Drone) drone.clone();
            droneToSave.setState(stateToChange);
            return droneRepository.save(droneToSave);
        } catch (CloneNotSupportedException exp) {
            log.error(exp);
        }
        return null;
    }


    @Override
    public Dispatch loadDrone(Dispatch dispatch) {
        return dispatchRepository.save(dispatch);
    }

    @Override
    public List<Medication> findMedicationByCode(List<String> medicineCodeList) {
        return medicationRepository.findMedicationByCode(medicineCodeList);
    }

    @Override
    public Dispatch dispatchDrone(Dispatch dispatch) {
        dispatch.setDeliveryStartTime(Timestamp.valueOf(LocalDateTime.now()));
        Drone deliveringDrone = changeDroneStatus(dispatch.getDrone(), DroneState.DELIVERED);
        dispatch.setDrone(deliveringDrone);
        return dispatchRepository.save(dispatch);
    }

}
