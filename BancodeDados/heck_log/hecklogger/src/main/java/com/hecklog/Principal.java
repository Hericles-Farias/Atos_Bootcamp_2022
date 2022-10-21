package com.hecklog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Principal {
    
    private static Logger  logger = LogManager.getLogger(Principal.class);
    public static void main(String[] args) {
        logger.debug("Debg msg heehe");
        logger.info("Info msg heehe");
        logger.error("Error msg heehe");
    }
}
