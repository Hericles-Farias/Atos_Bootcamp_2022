package com.br.atos2022.bss.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.atos2022.bss.models.electricVehicle;

public interface EVRepository extends JpaRepository<electricVehicle, java.lang.String> {

    Optional<electricVehicle> findById(String plate);
    
}
