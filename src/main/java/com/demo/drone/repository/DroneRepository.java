package com.demo.drone.repository;

import com.demo.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("from Drone d where d.serialNumber = :serialNumber")
    Drone findBySerialNumber(String serialNumber);

    @Query("from Drone d where d.state <> 'IDLE'")
    List<Drone> getNonIdealDrones();

}

