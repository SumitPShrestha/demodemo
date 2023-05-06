package com.demo.drone.controller;

import com.demo.drone.exception.DroneRegistrationException;
import com.demo.drone.exception.DroneStateException;
import com.demo.drone.model.Dispatch;
import com.demo.drone.model.Drone;
import com.demo.drone.model.DroneState;
import com.demo.drone.model.Medication;
import com.demo.drone.service.DroneDispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        Dispatch savedDispatch = droneDispatchService.getDispatchBySerialNumber(serialNumber);
        if (savedDispatch != null) {
            throw new DroneRegistrationException("drone-already-registered", String.format("Drone with SN %s is already registered", serialNumber), HttpStatus.ALREADY_REPORTED);
        }
        savedDispatch = droneDispatchService.registerDrone(serialNumber);
        return ResponseEntity.ok(savedDispatch);
    }

    @RequestMapping("load-drone/{serialNumber}")
    public ResponseEntity<Dispatch> doLoadDrone(@PathVariable String serialNumber, @RequestBody List<String> medicineCodeList) {
        Dispatch dispatch = droneDispatchService.getDispatchBySerialNumber(serialNumber);
        if (dispatch == null) {
            throw new DroneRegistrationException("drone-not-registered", String.format("drone with serial number %s is not registered", serialNumber), HttpStatus.NOT_FOUND);
        }
        Drone droneToLoad = dispatch.getDrone();
        if (droneToLoad.getBatteryCapacity() < 25) {
            throw new DroneStateException("Battery Capacity less then 25%, cannot load");
        }
        DroneState state = droneToLoad.getState();
        if (state != DroneState.IDLE) {
            throw new DroneStateException("Drone is not in ideal state");
        }
        List<Medication> medicationsToLoad =
                droneDispatchService.findMedicationByCode(medicineCodeList);
        Float totalWeight = medicationsToLoad.stream().map(m -> m.getWeight()).reduce(0F, Float::sum);
        if (totalWeight > droneToLoad.getWeightLimit()) {
            throw new DroneStateException(String.format("weight limit exceeded cannot load the medications." +
                            "Drone weight Capacity = %d gm. Current load weight: %f gm ",
                    droneToLoad.getWeightLimit(), totalWeight));
        }
        Drone loadingDrone = droneDispatchService.changeDroneStatus(droneToLoad, DroneState.LOADING);
        dispatch.setDrone(loadingDrone);
        dispatch.setMedications(medicationsToLoad.stream().map(m -> m.getCode()).toList());
        dispatch.setTotalWeight(totalWeight);
        Drone loadedDrone = droneDispatchService.changeDroneStatus(loadingDrone, DroneState.LOADED);
        dispatch.setDrone(loadedDrone);
        dispatch = droneDispatchService.loadDrone(dispatch);
        return ResponseEntity.ok(dispatch);
    }

    @RequestMapping("dispatch-drone/{serialNumber}")
    public ResponseEntity<Dispatch> dispatchDrone(@PathVariable String serialNumber) {
        Dispatch dispatch = droneDispatchService.getDispatchBySerialNumber(serialNumber);
        if (dispatch == null) {
            throw new DroneRegistrationException("drone-not-registered", String.format("drone with serial number %s is not registered", serialNumber), HttpStatus.NOT_FOUND);
        }
        Drone droneToDispatch = dispatch.getDrone();
        if (droneToDispatch.getState() != DroneState.LOADED) {
            throw new DroneStateException(String.format("drone with serial number %s is not loaded", serialNumber));
        }
        if (droneToDispatch.getBatteryCapacity() < 25) {
            throw new DroneStateException("Battery Capacity less then 25%, cannot load");
        }
        Drone deliveringDrone = droneDispatchService.changeDroneStatus(droneToDispatch, DroneState.DELIVERING);
        dispatch.setDrone(deliveringDrone);
        dispatch = droneDispatchService.dispatchDrone(dispatch);

        return ResponseEntity.ok(dispatch);
    }

    @RequestMapping("/call-back/{serialNumber}")
    public ResponseEntity<Dispatch> doCallBackDrone(@PathVariable String serialNumber) {
        Dispatch savedDispatch = droneDispatchService.getDispatchBySerialNumber(serialNumber);
        if (savedDispatch == null) {
            throw new DroneRegistrationException("drone-not-registered", String.format("Drone with SN %s is not registered yet", serialNumber), HttpStatus.ALREADY_REPORTED);
        }
        Drone callToCallBack = savedDispatch.getDrone();
        if (callToCallBack.getState() != DroneState.DELIVERING) {
            throw new DroneStateException(String.format("drone with serial number %s is not dispatched for delivery", serialNumber));
        }
        if (callToCallBack.getBatteryCapacity() < 25) {
            throw new DroneStateException("Battery Capacity less then 25%, cannot load");
        }
        Drone deliveredDrone = droneDispatchService.changeDroneStatus(callToCallBack, DroneState.DELIVERED);
        savedDispatch.setDrone(deliveredDrone);
        Dispatch returningDIspatch = droneDispatchService.callBackDrone(savedDispatch);
        Drone returningDrone = droneDispatchService.changeDroneStatus(returningDIspatch.getDrone(), DroneState.RETURNING);
        savedDispatch.setDrone(returningDrone);
        return ResponseEntity.ok(savedDispatch);
    }

    @RequestMapping("/list-loaded-medications/{serialNumber}")
    public ResponseEntity<List<Medication>> getLoadedMedicines(@PathVariable String serialNumber) {
        Dispatch savedDispatch = droneDispatchService.getDispatchBySerialNumber(serialNumber);
        if (savedDispatch == null) {
            throw new DroneRegistrationException("drone-not-registered", String.format("Drone with SN %s is not registered yet", serialNumber), HttpStatus.ALREADY_REPORTED);
        }
        Drone loadedDrone = savedDispatch.getDrone();
        if (loadedDrone.getState() == DroneState.IDLE || loadedDrone.getState() == DroneState.LOADING) {
            throw new DroneStateException(String.format("drone with serial number %s is not loaded", serialNumber));
        }
        List<Medication> loadedMedicines = droneDispatchService.getLoadedMedications(savedDispatch);
        return ResponseEntity.ok(loadedMedicines);
    }

}
