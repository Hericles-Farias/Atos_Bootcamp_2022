package com.br.atos2022.bss.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.atos2022.bss.models.Role;

@Repository
public interface roleRepository extends JpaRepository<Role,String>{
    
    Optional<Role> findByRoleID(String role_id);

}
