package com.br.atos2022.bss.controllers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

import com.br.atos2022.bss.dto.electricvehicleDTO;
import com.br.atos2022.bss.dto.schedulingDTO;
import com.br.atos2022.bss.mapper.electricVehicleMapper;
import com.br.atos2022.bss.mapper.schedulingMapper;
import com.br.atos2022.bss.models.cluster;
import com.br.atos2022.bss.models.electricVehicle;
import com.br.atos2022.bss.models.plan;
import com.br.atos2022.bss.models.scheduling;
import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.userRepository;

import lombok.extern.slf4j.Slf4j;

import com.br.atos2022.bss.repositories.EVRepository;
import com.br.atos2022.bss.repositories.clusterRepository;
import com.br.atos2022.bss.repositories.planRepository;
import com.br.atos2022.bss.repositories.schedulingRepository;

@RestController
@Slf4j
public class userController {
    
    @Autowired
    private electricVehicleMapper electricvehicleMapper;

    @Autowired
    public userRepository userRep;
    
    @Autowired
    public schedulingRepository schedRep;

    @Autowired
    public EVRepository eVRep;

    @Autowired
    public planRepository planRep;

    @Autowired
    public clusterRepository clusRep;
    
    @Autowired
    private schedulingMapper schedulingMapper;

    //PEGA TODOS OS USUARIOS DO BANCO DE DADOS
    @GetMapping(path="/user",produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<user> all(){
        List<user>users = userRep.findAll();
        log.info("Returning all users.");
        return users;
    }
    //CRIA UM NOVO USUARIO
    @PostMapping("/user")
    public String newUser(@RequestBody Map<String, String> json){
        user u = new user();
        u.setNickname(json.get("nickname"));
        u.setEmail(json.get("email"));
        u.setPassword(json.get("password"));
        plan p = planRep.findById(json.get("name")).orElseThrow(()->new planNotFoundException(json.get("name")));
        u.setCurrentPlan(p);
        userRep.save(u);
        log.info("Criando novo usuário.");
        return "Done!";
    }
    //Single Item
    //BUSCAR UM USUARIO ESPECIFICO DO BANCO
    @GetMapping("/user/{id}")
    public user searchUser(@PathVariable Integer id){
        log.info("Fetching specific user.");
        return userRep.findById(id).orElseThrow(()->new userNotFoundException(id));        
    }
    //REMOVE UM USUARIO ESPECIFICO
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Integer id){
        userRep.deleteById(id);
        log.info("Removing user.");
        return "User: "+id+" removed from db!";
    }

    //ADICIONA MAIS UM VE À UM USUARIO ESPECIFICO
    @PostMapping("/user/{id}/addEV")
    public String AddEVToUser(@RequestBody electricVehicle ev, @PathVariable Integer id){
        
        user u = userRep.findById(id).orElseThrow(()->new userNotFoundException(id));
        u.addEV(ev);
        userRep.save(u);
        log.info("Adding EV to user.");
        return "EV with plate: "+ev.getPlate()+" added to user: "+u.getNickname();
    }
    //REMOVE UM VE DE UM USUARIO ESPECIFICO
    @DeleteMapping("/user/{id}/rmvEV/{plate}")
    public String RmvEVFromUser(@PathVariable Integer id, @PathVariable String plate){
        user u = userRep.findById(id).orElseThrow(()->new userNotFoundException(id));
        electricVehicle ev = eVRep.findById(plate).orElseThrow(()->new EVNotFoundException(plate));
        u.removeEV(ev);
        userRep.save(u);
        log.info("Removing EV from users.");
        return "EV with plate: "+plate+" removed from user: "+id;
    }



    //TROCA DE PLANO
    @PostMapping("/user/{id}/plan")
    public String changeUserPlan(@RequestBody String name, @PathVariable Integer id){
        plan new_p = planRep.findById(name).orElseThrow(()->new planNotFoundException(name));
        user u = userRep.findById(id).orElseThrow(()->new userNotFoundException(id));
        plan old_p = u.getCurrentPlan();
        //remove the user from the plan he was at
        new_p.addUser(u);
        planRep.save(new_p);
        //u.setCurrentPlan(new_p);
        //old_p.removeUser(u);
        //add him to the new plan (inside of this it will also set the currentPlan to the new)
        //new_p.addUser(u);
        log.info("Changing plan of an user.");
        return "Plan from user: "+u.getNickname()+" updated to: "+new_p.getName();
    }
    //Get all EVS of a specific user
    @GetMapping("/user/{id}/evs")
    public List<electricvehicleDTO> get_evs(@PathVariable String id){
        user u =userRep.findById(Integer.parseInt(id)).orElseThrow(()->new userNotFoundException(Integer.parseInt(id)));
        
        List<electricvehicleDTO> evs = electricvehicleMapper.toElectricVehicleDTOs(u.getCars());
        //electricVehicle evk = u.getCars().get(0);
        //electricvehicleDTO evkdto = electricvehicleMapper.toElectricVehicleDTO(evk);
        //List<electricVehicle> = u.getCars();
        log.info("Returning all EVs from an user.");
        return evs;
    }

    //EDITA AS INFORMAÇOES ESPECÍFICAS DE UM CARRO DE UM EV
    @PutMapping("/user/{id}/evs/{plate}/update")
    public String updateEVInfo(@RequestBody Map<String,String> json, @PathVariable String id, @PathVariable String plate){
        StringBuilder s = new StringBuilder();
        s.append("Fields updated: ");
        Integer user_id = Integer.parseInt(id);
        user u = userRep.findById(user_id).orElseThrow(()->new userNotFoundException(user_id));
        //electricVehicle evk = u.getCars();
        electricVehicle old_ev = eVRep.findById(plate).orElseThrow(()->new EVNotFoundException(plate));
        electricVehicle ev = old_ev;
        //we first need to remove it, update it and then save it again!
        for (Map.Entry<String,String>entry : json.entrySet()){
            String key = entry.getKey();
            if (key.equals("plate")){
                ev.setPlate(entry.getValue());
                s.append(key);
                s.append(", ");
                
            }
            else if(key.equals("model")){
                ev.setModel(entry.getValue());
                s.append(key);
                s.append(", ");
            }else if(key.equals("brand")){
                ev.setModel(entry.getValue());
                s.append(key);
                s.append(", ");

            }else if(key.equals("batteryCapacity")){
                ev.setBatteryCapacity(Integer.parseInt(entry.getValue()));
                s.append(key);
                s.append(", ");

            }else if(key.equals("type")){
                ev.setType(entry.getValue());
                s.append(key);
                s.append(", ");
            }
            //u.updateEVInfo(ev);
            //userRep.save(u);
        }
        //eVRep.save(ev);
        u.removeEV(old_ev);
        userRep.save(u);
        u.addEV(ev);
        userRep.save(u);
        //u.updateEVInfo(ev);
        log.info("Editing EV info from an user.");
        return s.toString();
    }

    //SOLICITA UM AGENDAMETNO

    //SOLICITA OS CLUSTERS DISPONÍVEIS

    //CANCELA UM AGENDAMENTO
    @PostMapping("/user/{id}/cancell/{sched_id}")
    public String cancellScheduling(@PathVariable String id, @PathVariable String sched_id){
        //get the related user
        System.out.println(id);
        System.out.println(sched_id);

        user u = userRep.findById(Integer.parseInt(id)).orElseThrow(()->new userNotFoundException(Integer.parseInt(id)));
        //cluster c = clusRep.findById(null);

        scheduling sch = schedRep.findById(Integer.parseInt(sched_id)).orElseThrow(()->new schedNotFoundException(Integer.parseInt(sched_id)));
    
        LocalDateTime sch_ts = sch.getCluster().getDate().toLocalDateTime();
        //LocalDateTime sch_ts = sch.getCluster().getDate().toInstant().atZone(ZoneId.of("America/Sao_Paulo")).toLocalDateTime();
        //stem.out.println(sch.getCluster().getDate());
        //cluster c = clusRep.findById(sch.getCluster().getDate());
        //convert this timestamp to string and them
        //make it a LocalDateTime so we can use compore it the 
        //current time!
        // String mystr = sch.getCluster().getDate().toString();
        // mystr = mystr.substring(0, mystr.length() - 2);
        // System.out.println(mystr);
        cluster c = clusRep.findById(sch.getCluster().getDate()).orElseThrow(()->new clusterNotFoundException(sch.getCluster().getDate()));;
        System.out.println(c.getDate());
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime sch_ldt = LocalDateTime.now();
        //LocalDateTime now_utc0=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:m m:ss");
        // Date dx=new Date();
        // try{
        // dx = format1.parse(sch.getCluster().getDate().toString());
        // }catch (Exception e){}
        // System.out.println(dx);

        ////sch_ldt = LocalDateTime.parse(sch_ts,dateTimeFormatter);

        try{
         now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        }catch (Exception  e){}
        now = now.plusHours(4);//4 hours before the swapping
        //String now_as_string = now.format(dateTimeFormatter);
        Boolean isBefore = now.isBefore(sch_ts);
        System.out.println(isBefore);

        if (isBefore){

            sch.setStatus("CANCELLED");
            //sch.getCluster().setStatus(true);
            schedRep.save(sch);

            clusRep.deleteById(c.getDate());
            
            // cluster cnew = new cluster();
            // cnew.setStatus(true);
            // cnew.setDate(c.getDate());
            // cnew.setScheduling(sch);
            // clusRep.save(cnew);
            //then we can cancell it
            //u.removeSched(sch);
        }else {
            log.info("Could not cancell scheduling of an user.");
            return "Order can not be cancelled!";     
        }
        // //then update its order

        // //
        log.info("Cancelling scheduling from an user.");
        return "Order: "+id+" cancelled!";
    }

    @GetMapping("/user/{id}/schedules")
    public List<schedulingDTO> schedule(@PathVariable String id){

        user u =userRep.findById(Integer.parseInt(id)).orElseThrow(()->new userNotFoundException(Integer.parseInt(id)));
        List<schedulingDTO> scheds = schedulingMapper.toschedulingDTOs(u.getSchedules());
        return scheds;
    }

    
}
