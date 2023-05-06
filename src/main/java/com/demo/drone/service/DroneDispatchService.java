package com.demo.drone.service;

import com.demo.drone.model.Dispatch;
import com.demo.drone.model.Drone;
import com.demo.drone.model.DroneState;
import com.demo.drone.model.Medication;

import java.util.List;

public interface DroneDispatchService {
    List<Drone> getAllDrones();
    List<Medication> getAllMedications();

    Dispatch getDispatchBySerialNumber(String serialNumber);
    Dispatch registerDrone(String serialNumber);


    Drone changeDroneStatus(Drone drone, DroneState stateToChange);
    Dispatch loadDrone(Dispatch dispatch);

    List<Medication> findMedicationByCode(List<String> medicineCodeList);

    Dispatch dispatchDrone(Dispatch deliveringDrone);

    Dispatch callBackDrone(Dispatch savedDispatch);

    List<Medication> getLoadedMedications(Dispatch loadedDrone);
}
