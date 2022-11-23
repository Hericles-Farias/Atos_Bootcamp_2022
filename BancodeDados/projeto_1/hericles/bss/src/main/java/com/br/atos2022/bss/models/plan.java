package com.br.atos2022.bss.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name="Plan")
public class plan {

    //BRONZE/SILVER/GOLD
    @Id
    public java.lang.String name;

    @JsonIgnoreProperties("currentPlan")
    @OneToMany(mappedBy = "currentPlan", cascade=CascadeType.ALL, fetch = FetchType.LAZY)//cascade = CascadeType.ALL,orphanRemoval=true,)
    public List<user> users = new ArrayList<>();

    //THE HIGHER THE PLAN RANK THE LOWER THE ENERGY PRICE
    public Integer energyPrice;
    //THE HIGHER THE PLAN RANK THE LOWER THE FIXED COST
    public Integer fixedCost;
    //monthly/semesterly/annualy
    public String renewPeriod;

    public void addUser(user user){
        users.add(user);
        user.setCurrentPlan(this);

    }
    public void removeUser(user user){
        users.remove(user);
    }
        

}