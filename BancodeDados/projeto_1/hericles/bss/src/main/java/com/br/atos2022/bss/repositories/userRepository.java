package com.br.atos2022.bss.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.atos2022.bss.models.user;

@Repository
public interface userRepository extends JpaRepository<user, Integer> {
    
    Optional<user> findByUsername(String username);

}
