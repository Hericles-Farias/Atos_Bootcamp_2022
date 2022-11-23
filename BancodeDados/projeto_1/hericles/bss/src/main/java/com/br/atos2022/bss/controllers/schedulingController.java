package com.br.atos2022.bss.controllers;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.text.SimpleDateFormat;

import org.hibernate.bytecode.enhance.internal.tracker.SimpleFieldTracker;
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
import com.br.atos2022.bss.models.scheduling;
import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.userRepository;

import ch.qos.logback.core.encoder.EchoEncoder;
import lombok.extern.slf4j.Slf4j;

import com.br.atos2022.bss.repositories.EVRepository;
import com.br.atos2022.bss.repositories.clusterRepository;
import com.br.atos2022.bss.repositories.planRepository;
import com.br.atos2022.bss.repositories.schedulingRepository;

@RestController
@Slf4j
public class schedulingController {
    
    @Autowired
    public clusterRepository clusRep;

    @Autowired
    public schedulingRepository schedRep;

    @Autowired
    public userRepository userRep;

    @Autowired
    public planRepository planRep;


    private java.sql.Timestamp parseTimestamp(String timestamp){
        final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try{
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        }catch(ParseException e){
            throw new IllegalArgumentException(e);
        }
    }
    

    @PostMapping("/scheduling")
    public String newSchedule(@RequestBody Map<String,String> json){
        //@RequestBody user user
        //if we are receiving a date, let's first see if it does not 
        //already exists on our db, in that case, we can't use it!
        //Date mydate;
        Timestamp timestamp;
        Integer user_id = Integer.parseInt(json.get("user_id"));
        user u = userRep.findById(user_id).orElseThrow(()->new userNotFoundException(user_id));
        try{
            //mydate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(json.get("cluster_date"));
            //json.get("cluster_id"))
            //this DateUtil is a function to help dealing with the timestamp format
            //timestamp = new Timestamp(DateUtil.provideDateFormat().parse(json.get("cluster_date")).getTime());
            timestamp = parseTimestamp(json.get("cluster_date"));

            } catch (Exception e){
            return "Cluster in wrong Format!";
        }

        cluster c;
        try{
            c  = clusRep.findById(timestamp).orElseThrow(()->new clusterNotFoundException(timestamp));
            //If it did not break it means the cluster was already created (just in case we should see if
            //it is closed as well (it should be))
            if (!c.getStatus()){
                //this means we can not use this cluster, so let's ask the user to provide another one!
                return "Could not craete Order. Cluster already closed, plase provide an available cluster";
            }
        }catch (Exception e){
            //if it land here, this means the cluster do not exists, so let's create it in order to
            //pass it to the scheduling order!
            c = new cluster();
            c.setDate(timestamp);
            c.setStatus(true);
            //clusRep.save(c);
        }
        //craete the order
        scheduling sch = new scheduling();
        sch.setUser(u);
        sch.setStatus("CONFIRMED");
        //pass the clus to the scheduling order
        sch.setCluster(c);
        //and change its state to unavaible
        c.setStatus(false);
        clusRep.save(c);
        //then save the order!
        schedRep.save(sch);
        log.info("Creating new scheduling order for the user.");
        return "Scheduling Order: "+sch.getId()+" created for user: "+u.getNickname()+" in cluster: "+c.getDate();
        //return "Created cluster: "+c.getDate();
        }




}
