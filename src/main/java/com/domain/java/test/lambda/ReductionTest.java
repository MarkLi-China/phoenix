package com.domain.java.test.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/11/3
 */
public class ReductionTest {

    public static void main(String[] args) {

        List<Integer> list2 = Arrays.asList(2, 3, 4);
        //Here result will be 2*2 + 2*3 + 2*4 that is 18.
        int res = list2.parallelStream().reduce(2, (s1, s2) -> s1 * s2, (p, q) -> p + q);
        System.out.println(res);
        // 2 * 2 * 3 * 4 = 48
        int res1 = list2.stream().reduce(2, (s1, s2) -> s1 * s2, (p, q) -> p + q);
        System.out.println(res1);
        // 2+2 + 2+3 + 2+4 = 15
        int res2 = list2.parallelStream().reduce(2, (s1, s2) -> s1 + s2);
        System.out.println(res2);
        // 2 + 2 + 3 + 4 = 11
        int res3 = list2.stream().reduce(2, (s1, s2) -> s1 + s2);
        System.out.println(res3);

        List<String> list1 = Arrays.asList("Mohan", "Sohan", "Ramesh");
        String result = list1.parallelStream().reduce("-", (s1, s2) -> s1 + s2, (p, q) -> p + q);
        String result1 = list1.parallelStream().reduce("-", (s1, s2) -> s1 + s2);
        System.out.println(result);
        System.out.println(result1);
    }
}
