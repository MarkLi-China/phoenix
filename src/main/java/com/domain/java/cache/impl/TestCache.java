package com.domain.java.cache.impl;

import com.domain.java.cache.AbstractCallCache;
import com.domain.java.cache.AbstractLoadCache;

import java.util.concurrent.ExecutionException;

/**
 * com.domain.java.cache.impl
 * @author Mark Li
 * @version 1.0.0
 * @since 2015-12-17
 */
public class TestCache {

    static AbstractLoadCache<String, String> cache1 = new AbstractLoadCache<String, String>() {
        @Override
        protected String getData(String key) {

            System.out.println(123);
            return "xxx";
        }
    };

    static AbstractLoadCache<Integer, Integer> cache2 = new AbstractLoadCache<Integer, Integer>() {
        @Override
        protected Integer getData(Integer key) {

            System.out.println("yyy");
            return 456;
        }
    };

    static AbstractCallCache<String, String> cache3 = new AbstractCallCache<String, String>() {
        @Override
        public String getData(String key) {

            System.out.println(789);
            return "zzz";
        }
    };

    public static void main(String[] args) {

        try {
            System.out.println(cache1.getCache().get("xxx"));
            System.out.println(cache1.getCache().get("xxx"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(cache2.getCache().get(123));
            System.out.println(cache2.getCache().get(123));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(cache3.getCacheValue("xxx"));
            System.out.println(cache3.getCacheValue("xxx"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
