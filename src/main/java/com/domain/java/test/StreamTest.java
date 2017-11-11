package com.domain.java.test;

import com.domain.common.utils.JsonHelper2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO describe the file
 *
 * @author lijing
 * @version 1.0.0
 * @since 2017/11/5
 */
public class StreamTest {

    public static void main(String[] args) {


        Integer[] arr = new Integer[] {1, 2, 3, 4};
        // 2 + 1 + 2 + 3 + 4 = 12
        System.out.println(Arrays.stream(arr).reduce(2, (a, b) -> a + b));
        // 2+1 + 2+2 + 2+3 + 2+4 = 18
        System.out.println(Arrays.stream(arr).parallel().reduce(2, (a, b) -> a + b));
        // 2 + 1 + 2 + 3 + 4 = 12
        System.out.println(Arrays.stream(arr).reduce(2, (a, b) -> a + b, (a, b) -> a * b));
        // (2 + 1) * (2 + 2) * (2 + 3) * (2 + 4) = 360
        System.out.println(Arrays.stream(arr).parallel().reduce(2, (a, b) -> a + b, (a, b) -> a * b));
        System.out.println(Arrays.stream(arr).map(item -> 2 + item).reduce((a, b) -> a * b).get());

        List<Integer> list = Arrays.stream(arr).filter(a -> a > 2).collect(Collectors.toList());
        System.out.println(JsonHelper2.toJson(list));

    }
}
