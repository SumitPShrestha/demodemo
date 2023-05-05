package com.demo.drone.repository;

import com.demo.drone.model.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchRepository extends JpaRepository<Dispatch, Long> {

    @Query("from Dispatch d LEFT JOIN Drone dr on d.drone.serialNumber = dr.serialNumber where dr.serialNumber = :serialNumber")
    Dispatch findBySerialNumber(String serialNumber);

}

