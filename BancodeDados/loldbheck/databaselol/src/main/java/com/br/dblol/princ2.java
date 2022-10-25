package com.br.dblol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class princ2 {
    
    private static Logger  logger = LogManager.getLogger(princ2.class);
    public static void main(String[] args) {
        logger.debug("Debg msg heehe");
        logger.info("Info msg heehe");
        logger.error("Error msg heehe");
    }
}
