package com.meng.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloSlf4j {

    private static Logger logger = LoggerFactory.getLogger(HelloSlf4j.class);

    public static void main(String[] args){
        logger.error("sdsdsds");
    }

}
