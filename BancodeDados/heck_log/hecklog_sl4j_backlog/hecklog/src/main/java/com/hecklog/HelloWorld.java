package com.hecklog;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloWorld {
    
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    public static void main(String[] args) {
        logger.debug("Hello from Logback...");
        logger.debug("getNumber() : {}",getNumber());
        Scanner s = new Scanner(System.in);
        System.out.println("Entre com um  número: ");
        try {
            Integer m = Integer.parseInt(s.nextLine());
            s.close();
            System.out.println("Este é seu numero: "+m);
        }
        catch (NumberFormatException e) {
            logger.warn("Deu ruim: "+e);
            logger.error("Pegou fogo: "+e);
            
        }
    }
    static int getNumber(){
        return 5;
    }


}

