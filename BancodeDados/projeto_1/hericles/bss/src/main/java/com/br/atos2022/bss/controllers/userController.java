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

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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
import com.br.atos2022.bss.enums.RoleName;
import com.br.atos2022.bss.mapper.electricVehicleMapper;
import com.br.atos2022.bss.mapper.schedulingMapper;
import com.br.atos2022.bss.models.cluster;
import com.br.atos2022.bss.models.electricVehicle;
import com.br.atos2022.bss.models.plan;
import com.br.atos2022.bss.models.scheduling;
import com.br.atos2022.bss.models.user;
import com.br.atos2022.bss.repositories.userRepository;
import com.br.atos2022.bss.services.userService;

import io.swagger.models.Response;
import lombok.extern.slf4j.Slf4j;

import com.br.atos2022.bss.repositories.EVRepository;
import com.br.atos2022.bss.repositories.clusterRepository;
import com.br.atos2022.bss.repositories.planRepository;
import com.br.atos2022.bss.repositories.schedulingRepository;

@RestController
@Slf4j
public class userController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private electricVehicleMapper electricvehicleMapper;

    @Autowired
    public userService userServ;
    
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")//Só admins podem acessar esta parte!
    @GetMapping(path="/bss/user",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<user>> all(){
        List<user>users = userServ.findAll();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //String princName = auth.getName();
        log.info("Returning all users.");
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
    //CRIA UM NOVO USUARIO
    //ANYONE IS AUTHORIZED TO USE THIS ENDPOINT
    @PostMapping("/bss/user")
    public ResponseEntity<Object> newUser(@RequestBody Map<String, String> json){
        //CREATING THE USER OBJECT
        try{
            user u = new user();
            //GETTING DATA FROM THE REQUEST
            u.setNickname(json.get("nickname"));
            u.setUsername(json.get("username"));//that will be the e-mail
            u.setPassword(passwordEncoder.encode(json.get("password")));
            //RETRIEVING THE RESPECTIVE PLAN FROM DB
            //INITIALLY ALL ACCOUNTS START WITH A BRONZE PLAN (EXCEPT THE ADMIN THAT USES A SPECIAL ONE CALLED (ADMIN - We still need to create it))
            //plan p = planRep.findById(json.get("name")).orElseThrow(()->new planNotFoundException(json.get("name")));
            plan p = planRep.findById("BRONZE").orElseThrow(()->new planNotFoundException("BRONZE"));
            //ASSIGNING IT TO THE NEW USER
            u.setCurrentPlan(p);
            //SAVING THE USER IN THE DB
            userServ.save(u);
            //LOGGING OPERATION!
            log.info("User: "+u.getUsername()+ " Successfully Created");
            return ResponseEntity.status(HttpStatus.OK).body("User; "+u.getNickname()+ " Successfully Created");
        }catch (Exception e){
            log.info("Error during user creation: "+e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Creation Failed!");
        }
    }

    //Single Item
    //BUSCAR UM USUARIO ESPECIFICO DO BANCO - THIS PART HERE SHOULD ONLY B
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/bss/user/{id}")
    public ResponseEntity<?> searchUser(@PathVariable Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        //RETRIEVE INFO FROM THE LOGGED USER
        user user = userServ.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: "+username));

        if(user.getId().equals(id) || auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
            log.info("User: "+user.getNickname()+" retrieved from DB");
            //verifica se o usuário tem o mesmo id que o path variable passado ao e se o usuário é adm
            return ResponseEntity.accepted().body(user); 
        }else{
            log.info("Could not retrieve User: "+user.getNickname()+" from DB");
            return ResponseEntity.badRequest().body("User Not Allowed");
        }
            
    }
    //REMOVE UM USUARIO ESPECIFICO
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/bss/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id){
        Optional<user> u = userServ.findById(id);//.orElseThrow(()->new userNotFoundException(id));
        //if you add the ElseThrow you do not neeed the optional
        //if you use the Optional you need to add .get() at the Optional to get the entity inside!
        if(!u.isPresent()){
            log.info("Could not Delete User: "+u.get().getNickname()+" from DB!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found.");
        }
        userServ.delete(u.get());
        log.info("User: "+u.get().getNickname()+" Deleted from DB!");
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted Successfully");
    }

    //ADICIONA MAIS UM VE À UM USUARIO ESPECIFICO
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/bss/user/{id}/addEV")
    public ResponseEntity<?> AddEVToUser(@RequestBody electricVehicle ev, @PathVariable Integer id){
        
        //user u = userServ.findById(id).orElseThrow(()->new userNotFoundException(id));
        //u.addEV(ev);
        //log.info("Adding EV to user.");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        //RETRIEVE INFO FROM THE LOGGED USER
        user user = userServ.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: "+username));
        if(user.getId().equals(id) || auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
            //cuide que, caso o usuario logado seja o admin, entao temos que pegar o usuario com o id igual ao id do endpoint
            if (auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
                //caso seja um admin tentando alterar dados de outros usuários, permita essa acao mas altere o objeto de mudança para o user correto!
                if (!user.getId().equals(id)){
                    //se isto é falso, significa que o admin tá tentando alterar info de outro user
                    //neste caso temos que recuperar este user do banco
                    //para entao alterarmos ele e nao o usuario do próprio admin
                    String admin_user = user.getNickname();
                    user = userServ.findById(id).orElseThrow(()->new userNotFoundException(id));
                    log.info("Admin: "+admin_user+ " making changes on user: "+user.getNickname()+"!");
                }
            }
            user.addEV(ev);
            userServ.save(user);
            log.info("New EV added to User: "+user.getNickname()+" !");
            //verifica se o usuário tem o mesmo id que o path variable passado ao e se o usuário é adm
            return ResponseEntity.accepted().body("EV added to user!"); 
        }else{
            log.info("Could not add a new ev to User: "+user.getNickname()+" !");
            return ResponseEntity.badRequest().body("Could not add ev to user!");
        }
        //return "EV with plate: "+ev.getPlate()+" added to user: "+u.getNickname();
    }
    


    //REMOVE UM VE DE UM USUARIO ESPECIFICO
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/bss/user/{id}/rmvEV/{plate}")
    public ResponseEntity<?> RmvEVFromUser(@PathVariable Integer id, @PathVariable String plate){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        //RETRIEVE INFO FROM THE LOGGED USER
        user user = userServ.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: "+username));
        if(user.getId().equals(id) || auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
            //cuide que, caso o usuario logado seja o admin, entao temos que pegar o usuario com o id igual ao id do endpoint
            if (auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
                //caso seja um admin tentando alterar dados de outros usuários, permita essa acao mas altere o objeto de mudança para o user correto!
                if (!user.getId().equals(id)){
                    //se isto é falso, significa que o admin tá tentando alterar info de outro user
                    //neste caso temos que recuperar este user do banco
                    //para entao alterarmos ele e nao o usuario do próprio admin
                    String admin_user = user.getNickname();
                    user = userServ.findById(id).orElseThrow(()->new userNotFoundException(id));
                    log.info("Admin: "+admin_user+ " making changes on user: "+user.getNickname()+"!");
                }
            }
            electricVehicle ev = eVRep.findById(plate).orElseThrow(()->new EVNotFoundException(plate));
            user.removeEV(ev);
            userServ.save(user);
            log.info("Ev: "+ev.getPlate()+" removed from user: "+user.getNickname());
            return ResponseEntity.status(HttpStatus.OK).body("EV removed from user!");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not remove EV from user!");
        }
        //user u = userServ.findById(id).orElseThrow(()->new userNotFoundException(id));
        //u.removeEV(ev);
        //userServ.save(u);
        //return "EV with plate: "+plate+" removed from user: "+id;
    }



    //TROCA DE PLANO
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/bss/user/{id}/plan/{name}")
    public ResponseEntity<?> changeUserPlan(@PathVariable String name, @PathVariable Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        user user = userServ.findByUsername(username)
        .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: "+username));
        if(user.getId().equals(id) || auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
            //cuide que, caso o usuario logado seja o admin, entao temos que pegar o usuario com o id igual ao id do endpoint
            if (auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"))){
                //caso seja um admin tentando alterar dados de outros usuários, permita essa acao mas altere o objeto de mudança para o user correto!
                if (!user.getId().equals(id)){
                    //se isto é falso, significa que o admin tá tentando alterar info de outro user
                    //neste caso temos que recuperar este user do banco
                    //para entao alterarmos ele e nao o usuario do próprio admin
                    String admin_user = user.getNickname();
                    user = userServ.findById(id).orElseThrow(()->new userNotFoundException(id));
                    log.info("Admin: "+admin_user+ " making changes on user: "+user.getNickname()+"!");
                }
            }
            plan new_p = planRep.findById(name).orElseThrow(()->new planNotFoundException(name));
            //plan old_p = user.getCurrentPlan();
            new_p.addUser(user);
            planRep.save(new_p);
            //user.setCurrentPlan(new_p);
            //old_p.removeUser(user);
            //new_p.addUser(user);
            log.info("Plan of user: "+user.getNickname()+" Changed to: "+new_p.getName());
            return ResponseEntity.status(HttpStatus.OK).body("Plan of user: "+user.getNickname()+" Changed to: "+new_p.getName());
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not alter plan from user: "+user.getNickname());
        }
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // String username = auth.getName();
        // //RETRIEVE INFO FROM THE LOGGED USER
        // user user = userServ.findByUsername(username)
        // .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: "+username));

        // plan new_p = planRep.findById(name).orElseThrow(()->new planNotFoundException(name));
        // user u = userServ.findById(id).orElseThrow(()->new userNotFoundException(id));
        // plan old_p = u.getCurrentPlan();
        // //remove the user from the plan he was at
        // new_p.addUser(u);
        // planRep.save(new_p);
        //// u.setCurrentPlan(new_p);
        //// old_p.removeUser(u);
        //// add him to the new plan (inside of this it will also set the currentPlan to the new)
        //// new_p.addUser(u);
        
        //return "Plan from user: "+u.getNickname()+" updated to: "+new_p.getName();
    }
    //Get all EVS of a specific user
    
    // ** til here

    @GetMapping("/user/{id}/evs")
    public List<electricvehicleDTO> get_evs(@PathVariable String id){
        user u =userServ.findById(Integer.parseInt(id)).orElseThrow(()->new userNotFoundException(Integer.parseInt(id)));
        
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
        user u = userServ.findById(user_id).orElseThrow(()->new userNotFoundException(user_id));
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
        userServ.save(u);
        u.addEV(ev);
        userServ.save(u);
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

        user u = userServ.findById(Integer.parseInt(id)).orElseThrow(()->new userNotFoundException(Integer.parseInt(id)));
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

        user u =userServ.findById(Integer.parseInt(id)).orElseThrow(()->new userNotFoundException(Integer.parseInt(id)));
        List<schedulingDTO> scheds = schedulingMapper.toschedulingDTOs(u.getSchedules());
        return scheds;
    }

    
}
