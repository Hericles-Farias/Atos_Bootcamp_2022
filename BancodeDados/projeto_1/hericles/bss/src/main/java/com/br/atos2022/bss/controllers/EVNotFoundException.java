package com.br.atos2022.bss.controllers;

public class EVNotFoundException extends RuntimeException {
    EVNotFoundException(String plate){
        super("Could not find EV "+plate);
    }
}
