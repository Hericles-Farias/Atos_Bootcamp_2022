package com.br.atos2022.bss.controllers;

import java.util.Date;

public class clusterNotFoundException extends RuntimeException{
    clusterNotFoundException(Date date)
    {
        super("Could not find cluster: "+date);
    }

}
