package com.demo.drone.utils;

import com.demo.drone.model.Drone;
import com.demo.drone.repository.DroneRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
@Log4j2
public class BatteryChecker {
    @Autowired
    DroneRepository droneRepository;

    @Scheduled(fixedRate = 15000)
    public void burnBattery() {
        List<Drone> drones = droneRepository.getNonIdealDrones();
        for (Drone drone : drones) {
            int batteryCapacity = drone.getBatteryCapacity();
            if (batteryCapacity > 0) {
                batteryCapacity = batteryCapacity >= 10 ? batteryCapacity - 10 : 0;
                drone.setBatteryCapacity(batteryCapacity);
            }
            log.info(String.format("batteryCapacity of drone with SN %s is %d", drone.getSerialNumber(), drone.getBatteryCapacity()));
            droneRepository.save(drone);

        }
    }
}
