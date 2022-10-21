package com.hericlesjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import lombok.extern.slf4j.Slf4j;


public class Princ2 {
    private static Logger logger  = LoggerFactory.getLogger(Princ2.class);
    public static void main(String[] args) {
        logger.info("this is an info msg");
        logger.debug("this is a debug msg");
        logger.error("this is an error msg");

        String variable = "Hello John";
        logger.debug("Printing variable value {} ", variable);
    }
}
