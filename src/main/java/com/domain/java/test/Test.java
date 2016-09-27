package com.domain.java.test;

import java.util.ArrayList;
import java.util.List;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/9/27
 */
public class Test {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (Integer i: list) {
            System.out.println("i = " + i);
        }
    }
}
