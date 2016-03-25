package com.domain.java.base;

import org.apache.commons.lang3.StringUtils;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleTest extends BaseTest {

    @Test
    public void test() {

        System.out.println("test");
    }

    @Test
    @Ignore
    public void testIgnore() {

        System.out.println("testIgnore");
    }

    @Test
    public void testSomething() {

        System.out.println(StringUtils.isBlank(null)); // true
        System.out.println(StringUtils.isBlank("")); // true
        System.out.println(StringUtils.isBlank(" ")); //                   true
        System.out.println(StringUtils.isBlank("bob")); // false
        System.out.println(StringUtils.isBlank("  bob  ")); // false

        System.out.println(StringUtils.isEmpty(null)); // true
        System.out.println(StringUtils.isEmpty("")); // true
        System.out.println(StringUtils.isEmpty(" ")); //                   false
        System.out.println(StringUtils.isEmpty("bob")); // false
        System.out.println(StringUtils.isEmpty("  bob  ")); // false
    }
}
