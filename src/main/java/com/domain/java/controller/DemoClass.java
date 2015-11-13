package com.domain.java.controller;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-19
 */
public class DemoClass {

    public static void main(String[] args) {

        DemoClass demoC = new DemoClass();
        demoC.test(1);
        demoC.test(2.1);
        demoC.test(1.0);
    }

    private void test(Object obj) {

        System.out.println("obj = [" + obj + "]");
    }

    private void test(Integer i) {

        System.out.println("i = [" + i + "]");
    }
}
