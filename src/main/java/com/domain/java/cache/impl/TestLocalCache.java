package com.domain.java.cache.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * com.domain.java.cache.impl
 * @author Mark Li
 * @version 1.0.0
 * @since 2015-12-17
 */
@Component
public class TestLocalCache implements InitializingBean {

    private static LoadingCache<String, Object> localCache;

    public Object getValue(String key) {

        try {
            localCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        localCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String key) throws Exception {

                        System.out.println("first time");
                        return 123;
                    }
                });
    }
}
