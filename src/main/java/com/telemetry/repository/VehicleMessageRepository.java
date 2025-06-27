package com.telemetry.repository;

import com.telemetry.model.VehicleMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMessageRepository extends JpaRepository<VehicleMessage, Long> {
}

