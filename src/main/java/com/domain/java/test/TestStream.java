package com.domain.java.test;

import com.domain.common.utils.JsonHelper2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/10/13
 */
public class TestStream {

    public static void main(String[] args) {

        List<String> list01 = Stream.of("a","b","c","b")
                .distinct()
                .collect(Collectors.toList());
        System.out.println(JsonHelper2.toJson(list01));

        List<Integer> list02 = IntStream.range(1,10).boxed()
                .filter( i -> i % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(JsonHelper2.toJson(list02));

        List list03 = Stream.of('a','b','c')
                .map(Object::hashCode)
                .collect(Collectors.toList());
        System.out.println(JsonHelper2.toJson(list03));

        List<Integer> list04 = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        )
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        System.out.println(JsonHelper2.toJson(list04));

        List list05 = Stream.of('a','b','c')
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(JsonHelper2.toJson(list05));
    }
}
