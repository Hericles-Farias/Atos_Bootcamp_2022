package com.br.atos2022.bss.controllers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.atos2022.bss.models.cluster;
import com.br.atos2022.bss.models.electricVehicle;
import com.br.atos2022.bss.models.plan;
import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.userRepository;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.br.atos2022.bss.repositories.EVRepository;
import com.br.atos2022.bss.repositories.clusterRepository;
import com.br.atos2022.bss.repositories.planRepository;
import com.br.atos2022.bss.repositories.schedulingRepository;

@RestController
@Slf4j
public class clusterController {
    
    @Autowired
    public schedulingRepository schedRep;

    @Autowired
    public clusterRepository clusRep;

    private java.sql.Timestamp parseTimestamp(String timestamp){
        final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try{
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        }catch(ParseException e){
            throw new IllegalArgumentException(e);
        }
    }

    public List<String> addMinutes(int dt, int T,  Date date){

        List<String> dates = new ArrayList<>();
        long curTimeInMs = date.getTime();
        for(int i=0;i<T;i++){
            Date dateAfter = new Date(curTimeInMs + (i*dt*60000));
            String dateAfterString = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dateAfter);
            dates.add(dateAfterString);
            //curTimeInMs = dateAfter.getTime();
        }
        
        return dates;

    }


    @PostMapping("/cluster/allwithin")
    public List<String> getAllWithinRange(@RequestBody Map<String,String>json){


        //Timestamp start = parseTimestamp(json.get("start"));
        //Timestamp end = parseTimestamp(json.get("end"));
        //Integer dt = 15;

        // 1 - first get all possible dates whithin this range!
        String k1 = json.get("start");
        //Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(k1).orElseThrow(()->new IllegalArgumentException("Deu ruim"));
        Date start;
        try{
            start = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(k1); 
        }catch (ParseException e){
            throw new IllegalArgumentException(e);
        }
        List<String> dates =addMinutes(15, 56, start);
        // then all the already taken clusters whithin this range!
        Date x1 = new Date();
        String x1str;
        Boolean stat;
        for (cluster c: clusRep.findAll()){
            x1.setTime(c.getDate().getTime());
            x1str = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(x1);
            stat = c.getStatus();
            if (!stat){
                if(dates.contains(x1str)){
                    dates.remove(String.valueOf(x1str));
                }
            }
        }
        //if there are, them remove them from the initial list
        //then return the rest!
        log.info("Returning all clusters within specified range.");
        return dates;


    }

    // //ADDS NEW CLUSTER TO DB
    // @PostMapping("/cluster")
    // public String newCluster(@RequestBody Map<String,String> json){
    //     cluster c = new cluster();
    //     Date date = json.get("date");
    //     c.setStatus(true);
    //     //c.setDate();
    // }

}
