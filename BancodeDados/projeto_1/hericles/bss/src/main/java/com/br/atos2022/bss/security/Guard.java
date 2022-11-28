package com.br.atos2022.bss.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.br.atos2022.bss.controllers.userNotFoundException;
import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.userRepository;
import com.br.atos2022.bss.services.userService;

@Component
public class Guard {
    @Autowired
    private userService userServ;


    public Boolean checkUserId(Authentication auth, int id){

        String name = auth.getName();
        System.out.println(name);
        //Optional<user> u = userServ.findById(id);
        
        return true;
    }
}
