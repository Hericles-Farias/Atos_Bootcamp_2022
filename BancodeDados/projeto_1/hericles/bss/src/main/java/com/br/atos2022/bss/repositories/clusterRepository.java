package com.br.atos2022.bss.repositories;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.atos2022.bss.models.cluster;

@Repository
public interface clusterRepository extends JpaRepository<cluster,Timestamp> {
    Optional<cluster> findById(Date date);
    List<cluster> findAll();

    

}
