package com.br.atos2022.bss.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="User")
public class user {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer id;

    public String nickname;
    public String email;
    public String password;
    
    @JsonIgnoreProperties("users")
    @ManyToOne(fetch=FetchType.LAZY, optional=true)
    @JoinColumn(name="plan_name",nullable = false)
    public plan currentPlan;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    public List<scheduling> schedules = new ArrayList<>();
    @JsonIgnoreProperties("user")//esse cara previni a recursao infinita devido ao onetomany e manytoone
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL, orphanRemoval=true,fetch = FetchType.LAZY)
    public List<electricVehicle> cars = new ArrayList<>(); 

    public void addEV(electricVehicle ev){
        cars.add(ev);
        ev.setUser(this);
    }

    // public String updateEVInfo(electricVehicle new_ev){
    //     Integer idx=0;
    //     for(electricVehicle ev : cars){
    //         //System.out.println(ev);
    //         if(ev.getPlate().equals(new_ev.getPlate())){
    //             cars.set(idx,new_ev);
    //             new_ev.setUser(this);
    //             return "Child updated";
    //         }
    //         idx+=1;
    //     }
    //     return "Child Not Found!";
    // }

    public void removeEV(electricVehicle ev){
        cars.remove(ev);
        ev.setUser(null);
    }

    public void addSched(scheduling sch){
        schedules.add(sch);
        sch.setUser(this);
    }

    public void removeSched(scheduling sch){
        schedules.remove(sch);
        sch.setUser(null);
    }

}
