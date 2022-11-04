package buffet;

import java.lang.Math;

public class calcPrice {
    
    public String name;
    public Integer guests;
    public Boolean dessert;
    public Double valorTotal;
    public Double valorPorConv;
    public Integer waiters;

    public calcPrice(String name, Integer guests, Boolean dessert){
        this.name = name;
        this.guests=guests;
        this.dessert=dessert;
    }

    public void doCalc(){
        //contants
        Double hotMeal=22.90;
        Double dessertTax=0.1;
        Integer waitersTax=250;
        
        Double valuePerGuests=this.guests * hotMeal;
        this.waiters = (int) Math.ceil((double) this.guests/15);
        Integer waitersTotal=this.waiters*waitersTax;
        this.valorTotal = (valuePerGuests*(1+dessertTax)+waitersTotal);
        this.valorPorConv = this.valorTotal/this.guests;
    }

    public String getName() {
        return name;
    }

    public Integer getGuests() {
        return guests;
    }

    public Boolean getDessert() {
        return dessert;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Integer getWaiters() {
        return waiters;
    }

    public Double getValorPorConv() {
        return valorPorConv;
    }
    




}
