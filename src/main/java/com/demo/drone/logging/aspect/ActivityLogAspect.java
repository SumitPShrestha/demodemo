package com.demo.drone.logging.aspect;

import com.demo.drone.dto.ActivityLogDTO;
import com.demo.drone.model.*;
import com.demo.drone.repository.ActivityLogRepository;
import com.demo.drone.repository.MedicationRepository;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Aspect
@Log4j2
public class ActivityLogAspect {
    @Autowired
    ActivityLogRepository activityLogRepository;
    @Autowired
    MedicationRepository medicationRepository;

    @Pointcut("execution(* *..registerDrone(String))")
    public void logRegisterDrone() {
    }

    @Pointcut("execution(* com.demo.drone.service.DroneDispatchService.dispatchDrone(..))")
    public void logDispatchDrone() {
    }

    @Pointcut("execution(* *..changeDroneStatus(..))")
    public void logDroneStatusChange() {
    }

    @Pointcut("execution(* *..loadDrone(..))")
    public void logDroneLoad() {

    }

    @Pointcut("execution(* com.demo.drone.service.DroneDispatchService.callBackDrone(..))")
    public void logDroneCallBack() {
    }


    @AfterReturning("logRegisterDrone()")
    public void afterDroneRegistered(JoinPoint thisJoinPoint) {
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        activityLogDTO.setActivity(String.format("drone with SN %s registered", (String) thisJoinPoint.getArgs()[0]));
        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivity(activityLogDTO.toString());
        activityLog.setActivityTime(Timestamp.valueOf(LocalDateTime.now()));
        log.info(activityLogDTO.toString());
        activityLogRepository.save(activityLog);
    }

    @Before(value = "logDroneStatusChange()")
    public void logDroneStatusChange(JoinPoint thisJoinPoint) {
        Drone inputDrone = (Drone) thisJoinPoint.getArgs()[0];
        DroneState afterState = (DroneState) thisJoinPoint.getArgs()[1];
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        String activity = String.format("State of drone with SN %s is changed", inputDrone.getSerialNumber());
        activityLogDTO.setActivity(activity);
        activityLogDTO.setBefore(inputDrone.getState().name());
        activityLogDTO.setAfter(afterState.name());
        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivity(activityLogDTO.toString());
        activityLog.setActivityTime(Timestamp.valueOf(LocalDateTime.now()));
        log.info(activityLogDTO.toString());
        activityLogRepository.save(activityLog);
    }

    @AfterReturning(value = "logDispatchDrone()", returning = "result")
    public void logDispatchDrone(JoinPoint thisJoinPoint, Object result) {
        Dispatch dispatch = (Dispatch) result;
        Drone deliveringDrone = dispatch.getDrone();
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        String activity = String.format("Done with SN %s dispatched for delivery", deliveringDrone.getSerialNumber());
        activityLogDTO.setActivity(activity);
        activityLogDTO.setBefore(String.format("{\"State\":\" %s \"}", DroneState.LOADED.name()));
        activityLogDTO.setAfter(String.format("{\"State\":\" %s \",\"Delivery Start Time\":\" %s \"}", DroneState.DELIVERING.name(), dispatch.getDeliveryStartTime().toString()));
        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivity(activityLogDTO.toString());
        activityLog.setActivityTime(Timestamp.valueOf(LocalDateTime.now()));
        log.info(activityLogDTO.toString());
        activityLogRepository.save(activityLog);
    }

    @AfterReturning(value = "logDroneLoad()", returning = "dispatch")
    public void logDroneLoad(JoinPoint thisJoinPoint, Object dispatch) {
        Dispatch beforeDispatch = (Dispatch) thisJoinPoint.getArgs()[0];
        Dispatch dispatchToLog = (Dispatch) dispatch;
        Drone returnedDrone = (Drone) dispatchToLog.getDrone();
        List<Medication> loadedMedicine = medicationRepository.findMedicationByCode(dispatchToLog.getMedications());
        String medicationsNames = loadedMedicine.stream()
                .map(medication -> medication.getName())
                .collect(Collectors.joining(","));
        String activity = String.format("Drone with SN %s is loaded with medicines %s", returnedDrone.getSerialNumber(), medicationsNames);
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        activityLogDTO.setActivity(activity);
        activityLogDTO.setBefore(String.format("{\"State\":\" %s \"}", DroneState.LOADING.name()));
        activityLogDTO.setAfter(String.format("{\"State\":\" %s \",\"Medication\":\" %s \"}", DroneState.LOADED.name(), medicationsNames));
        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivity(activityLogDTO.toString());
        activityLog.setActivityTime(Timestamp.valueOf(LocalDateTime.now()));
        log.info(activityLogDTO.toString());
        activityLogRepository.save(activityLog);
    }

    @AfterReturning(value = "logDroneCallBack()", returning = "result")
    public void logDroneCallBack(JoinPoint thisJoinPoint, Object result) {
        Dispatch dispatch = (Dispatch) result;
        Drone deliveringDrone = dispatch.getDrone();
        ActivityLogDTO activityLogDTO = new ActivityLogDTO();
        String activity = String.format("Done with SN %s completed delivery and is returning to warehouse", deliveringDrone.getSerialNumber());
        activityLogDTO.setActivity(activity);
        activityLogDTO.setBefore(String.format("{\"State\":\" %s \"}", DroneState.DELIVERING.name()));
        activityLogDTO.setAfter(String.format("{\"State\":\" %s \",\"Delivery End Time\":\" %s \"}", DroneState.RETURNING.name(), dispatch.getDeliveryEndTime().toString()));
        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivity(activityLogDTO.toString());
        activityLog.setActivityTime(Timestamp.valueOf(LocalDateTime.now()));
        log.info(activityLogDTO.toString());
        activityLogRepository.save(activityLog);
    }

}
