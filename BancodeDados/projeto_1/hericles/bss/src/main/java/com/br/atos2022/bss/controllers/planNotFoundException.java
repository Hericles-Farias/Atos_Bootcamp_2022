package com.br.atos2022.bss.controllers;

public class planNotFoundException extends RuntimeException {
    planNotFoundException(String name){
        super("Could not find plan: "+name);
    }
}
