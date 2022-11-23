package com.br.atos2022.bss.dto;

import lombok.Data;

@Data
public class planDTO {
    private String name;
    //THE HIGHER THE PLAN RANK THE LOWER THE ENERGY PRICE
    public Integer energyPrice;
    //THE HIGHER THE PLAN RANK THE LOWER THE FIXED COST
    public Integer fixedCost;
    //monthly/semesterly/annualy
    public String renewPeriod;


}
