package com.br.atos2022.bss.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.atos2022.bss.enums.RoleName;
import com.br.atos2022.bss.models.Role;
import com.br.atos2022.bss.models.plan;
import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.planRepository;
import com.br.atos2022.bss.repositories.roleRepository;
import com.br.atos2022.bss.repositories.userRepository;

@Component
public class DataLoader implements ApplicationRunner {
    
    private planRepository planRep;

    @Autowired
    private roleRepository roleRep;

    @Autowired
    private userRepository userRep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(planRepository planRep){
        this.planRep=planRep;

    }
    public void run(ApplicationArguments args){
        //criando os planos logo de cara
        plan p = new plan();
        p.setName("BRONZE");
        p.setEnergyPrice(21);
        p.setFixedCost(10);
        p.setRenewPeriod("Monthly");
        planRep.save(p);
        plan p2 = new plan();
        //second plan
        p2.setName("SILVER");
        p2.setEnergyPrice(19);
        p2.setFixedCost(9);
        p2.setRenewPeriod("Semesterly");
        planRep.save(p2);

        //cria as roles logo de cara
        //FOR THE ADMIN
        Role roleAdmin = new Role();
        roleAdmin.setRoleID("AdminRole");
        roleAdmin.setRoleName(RoleName.ROLE_ADMIN);
        roleRep.save(roleAdmin);
        //FOR THE USER
        Role roleUser = new Role();
        roleUser.setRoleID("UserRole");
        roleUser.setRoleName(RoleName.ROLE_USER);
        roleRep.save(roleUser);

        
        //criando o admin logo de cara
        // user userAdmin = new user();
        // userAdmin.setNickname("Hericles Farias");
        // userAdmin.setPassword(passwordEncoder.encode("senha000"));
        // userAdmin.setUsername("Hericles");
        // userAdmin.setCurrentPlan(p2);
        // userAdmin.addRole(roleAdmin);
        // userRep.save(userAdmin);
        // //e um user qualquer

        // user userUser = new user();
        // userUser.setNickname("Ciclano de Tal");
        // userUser.setPassword(passwordEncoder.encode("senha456"));
        // userUser.setUsername("Ciclano");
        // userUser.setCurrentPlan(p);
        // userUser.addRole(roleUser);
        // userRep.save(userUser);

    }

    
}
