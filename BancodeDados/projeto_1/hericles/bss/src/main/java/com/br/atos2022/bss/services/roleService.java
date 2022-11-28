package com.br.atos2022.bss.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.atos2022.bss.models.Role;
import com.br.atos2022.bss.repositories.roleRepository;

@Service
public class roleService {
    
    @Autowired 
    private roleRepository roleRep;


    public List<Role> findAll(){
        
        return roleRep.findAll();
    }

    public Optional<Role> findByRoleID(String role_id){

        return roleRep.findByRoleID(role_id);
    }

    // public Optional<> findByUsername(String username){
    //     return userRep.findByUsername(username);
    // }


    @Transactional // é sempre bom botar ele em cima de método de modificação do BD para caso de algo ruim sempre exita uma rollback!
    public Role save(Role role){
        //you could also add more steps here if needed!
        return roleRep.save(role);
    }

    @Transactional
    public void delete(Role role){
        roleRep.delete(role);
    }

}
