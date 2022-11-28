package com.br.atos2022.bss.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.atos2022.bss.models.scheduling;


@Repository
public interface schedulingRepository extends JpaRepository<scheduling,Integer> {
    Optional<scheduling> findById(Integer id);
    List<scheduling> findAll();
}
