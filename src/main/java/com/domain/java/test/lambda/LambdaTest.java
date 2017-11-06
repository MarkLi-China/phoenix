package com.domain.java.test.lambda;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/10/31
 */
public class LambdaTest {

    public static void main(String[] args) {

        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null)
                .distinct()
                .mapToInt(num -> num * 2)
                .peek(System.out::println).skip(2)
                .limit(4).sum());

        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
        collect(() -> new ArrayList<Integer>(),
        (list, item) -> list.add(item),
        (list1, list2) -> list1.addAll(list2));

        Object o = (Runnable) () -> System.out.println("Howdy, world!");
    }
}
