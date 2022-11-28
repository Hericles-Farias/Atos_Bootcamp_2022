package com.br.atos2022.bss.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.roleRepository;
import com.br.atos2022.bss.repositories.userRepository;

@Service
public class userService {
    
    @Autowired 
    private userRepository userRep;

    @Autowired
    private roleRepository roleRep;

    

    public List<user> findAll(){
        
        return userRep.findAll();
    }

    public Optional<user> findById(Integer id){

        return userRep.findById(id);
    }

    public Optional<user> findByUsername(String username){
        return userRep.findByUsername(username);
    }

    @Transactional // é sempre bom botar ele em cima de método de modificação do BD para caso de algo ruim sempre exita uma rollback!
    public user save(user user){
        //you could also add more steps here if needed!
        return userRep.save(user);
    }

    @Transactional
    public void delete(user user){
        userRep.delete(user);
    }




}
