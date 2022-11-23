package com.br.atos2022.bss.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.atos2022.bss.dto.planDTO;
import com.br.atos2022.bss.mapper.planMapper;
import com.br.atos2022.bss.models.plan;
import com.br.atos2022.bss.repositories.planRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class planController {
    
    @Autowired
    public planMapper planMapp;

    @Autowired
    public planRepository planRep;

    @PostMapping("/plan")
    public String newPlan(@RequestBody com.br.atos2022.bss.models.plan plan){
        planRep.save(plan);
        log.info("Creating a new plan.");
        return "Plan: "+plan.getName()+" saved to DB!";
    }

    @GetMapping("/plan")
    public @ResponseBody List<planDTO> all(){
        List<plan> plans = planRep.findAll();
        List<planDTO> planDTOs= planMapp.toplanDTOs(plans);
        log.info("Getting all plans.");
        return planDTOs;    
    }
    @DeleteMapping("/plan/{name}")
    public String deletePlan(@PathVariable String name){
        planRep.deleteById(name);
        log.info("Deleting a plan.");
        return "Plan: "+name+" removed from db";
    }


    //UPDATING BASED ON PROVIDED FIELDS
    @PostMapping("/plan/{name}/update")
    public String updatePlan(@RequestBody Map<String,String>json, @PathVariable String name){
        //fields we can change=energyPrice, fixedCost, renewPeriod
        StringBuilder s = new StringBuilder();
        s.append("Fields updated: ");
        //List<String> fields = new ArrayList<>();
        plan p = planRep.findById(name).orElseThrow(()-> new planNotFoundException(name));
        for (Map.Entry<String,String>entry : json.entrySet()){
            String key = entry.getKey();
            if (key.equals("energyPrice")){
                Integer energyPrice=Integer.parseInt(entry.getValue());
                p.setEnergyPrice(energyPrice);
                s.append(key);
                s.append(", ");
                planRep.save(p);
                //fields.add(key);
            }
            else if (key.equals("fixedCost")){
                Integer fixedCost=Integer.parseInt(entry.getValue());
                p.setFixedCost(fixedCost);
                s.append(key);
                s.append(", ");
                planRep.save(p);
                //fields.add(key);
            }
            else if (key.equals("renewPeriod")){
                String renewPeriod = entry.getValue();
                p.setRenewPeriod(renewPeriod);
                s.append(key);
                s.append(", ");
                planRep.save(p);
                //fields.add(key);
            }
            
        }
        log.info("Updating a new plan.");
        return s.toString();
    }


}
