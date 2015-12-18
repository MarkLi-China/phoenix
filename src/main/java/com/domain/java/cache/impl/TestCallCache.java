package com.domain.java.cache.impl;

import com.domain.java.cache.AbstractCallCache;

import java.util.concurrent.ExecutionException;

/**
 * com.domain.java.cache.impl
 * @author Mark Li
 * @version 1.0.0
 * @since 2015-12-17
 */
public class TestCallCache extends AbstractCallCache<String, String> {

    @Override
    public String getData(String key) {

        System.out.println(123);
        return "xxx";
    }

    public static void main(String[] args) {

        TestCallCache callCache = new TestCallCache();
        try {
            callCache.getCacheValue("xx");
            callCache.getCacheValue("xx");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
