package com.br.atos2022.bss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.br.atos2022.bss.models.plan;
import com.br.atos2022.bss.repositories.planRepository;

@Component
public class DataLoader implements ApplicationRunner {
    
    private planRepository planRep;

    @Autowired
    public DataLoader(planRepository planRep){
        this.planRep=planRep;

    }
    public void run(ApplicationArguments args){
        plan p = new plan();
        p.setName("BRONZE");
        p.setEnergyPrice(21);
        p.setFixedCost(10);
        p.setRenewPeriod("Monthly");
        planRep.save(p);

        //second plan
        p.setName("SILVER");
        p.setEnergyPrice(19);
        p.setFixedCost(9);
        p.setRenewPeriod("Semesterly");
        planRep.save(p);
    }

    
}
