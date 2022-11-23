package com.br.atos2022.bss.controllers;

public class userNotFoundException extends RuntimeException {
    userNotFoundException(Integer id){
        super("Could not find User "+id);
    }
}
