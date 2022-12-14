package com.br.atos2022.bss.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.atos2022.bss.models.plan;


@Repository
public interface planRepository extends JpaRepository<plan,java.lang.String> {
    
    Optional<plan> findById(String name);
    List<plan> findAll();
}
