package com.domain.java.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * com.domain.java.cache
 * @author Mark Li
 * @version 1.0.0
 * @since 2015-12-17
 */
public abstract class AbstractCallCache<K, V> {

    Cache<K, V> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build();

    public V getCacheValue(final K key) throws ExecutionException {

        return cache.get(key, new Callable<V>() {
            @Override
            public V call() throws Exception {

                return getData(key);
            }
        });
    }

    protected abstract V getData(K key);

    public Cache<K, V> getCache() {

        return cache;
    }

    public void setCache(Cache<K, V> cache) {

        this.cache = cache;
    }
}
