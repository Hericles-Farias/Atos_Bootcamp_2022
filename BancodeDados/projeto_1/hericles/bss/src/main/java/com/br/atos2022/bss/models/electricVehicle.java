package com.br.atos2022.bss.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="ElectricVehicle")
public class electricVehicle {
    
    @Id
    public java.lang.String plate;

    public String model;
    public String brand;
    
    @JsonIgnoreProperties("cars")
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    public user user;

    // @OneToOne(cascade=CascadeType.ALL)
    // @JoinColumn(name="battery_id", referencedColumnName = "id")
    public Integer batteryCapacity;
    public String type;

    public electricVehicle(String plate, String model, String brand, Integer batteryCapacity, String type){
        this.plate=plate;
        this.model=model;
        this.brand=brand;
        this.batteryCapacity=batteryCapacity;
        this.type=type;
    }

    @Override
    public boolean equals(Object o){
        if (this ==o) return true;
        if (!(o instanceof electricVehicle)) return false;
        return plate !=null && plate.equals(((electricVehicle) o).getPlate());
    }

    @Override
    public int hashCode(){
        return getClass().hashCode();
    }

}
