package com.domain.java.base;

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
}
