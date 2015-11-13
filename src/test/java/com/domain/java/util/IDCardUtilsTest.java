package com.domain.java.util;

import org.junit.Test;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-9-21
 */
public class IDCardUtilsTest {

    @Test
    public void conver15CardTo18Test() {

        String id18 = IDCardUtils.conver15CardTo18("123456789876543");
        System.out.println("id18 : " + id18);
    }

    @Test
    public void validateCardTest() {

        boolean result = IDCardUtils.validateCard("420881199005153331");
        System.out.println("result : " + result);
    }

    @Test
    public void getAgeByIdCardTest() {

        int age = IDCardUtils.getAgeByIdCard("420881199005153331");
        System.out.println("age : " + age);
    }
}
