package com.br.atos2022.bss.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.atos2022.bss.models.user;

public interface userRepository extends JpaRepository<user, Integer> {
    List<user> findAll();
    Optional<user> findById(Integer id);

}
