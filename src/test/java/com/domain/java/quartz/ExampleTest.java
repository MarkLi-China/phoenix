package com.domain.java.quartz;

import org.junit.Test;

/**
 * com.domain.java.quartz
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/1/6
 */
public class ExampleTest {


    @Test
    public void testRun() {

        Example example = new Example();
        try {
            example.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
