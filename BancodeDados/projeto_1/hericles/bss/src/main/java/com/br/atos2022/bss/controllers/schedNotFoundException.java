package com.br.atos2022.bss.controllers;

public class schedNotFoundException extends RuntimeException{
    schedNotFoundException(Integer id){
        super("Could not find scheduling: "+id);
    }

}
