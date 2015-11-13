package com.domain.java.base;

import com.domain.java.service.DefaultService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExampleTest extends BaseTest {

    @Autowired
    DefaultService defaultService;

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
