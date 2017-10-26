package com.domain.java.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/3/23
 */
public class HelloWorld {

    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {

        Integer num = 1;
        String name = "Hello World";
        logger.debug("num set to {}. name set to {}.", num, name);
    }
}

